# Calley Teams Automation

Selenium + TestNG automation framework for the **Calley Team Account Setup** flow on [app.getcalley.com](https://app.getcalley.com), built using the Page Object Model (POM) design pattern with Java and Maven.

This project automates four core user journeys:
1. **User Registration** (including WhatsApp OTP verification and free trial plan activation)
2. **User Login**
3. **Add Agent**
4. **CSV Upload via Power Import**

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test framework & assertions |
| Maven | Build & dependency management |
| WebDriverManager | Auto-manages ChromeDriver binaries |
| Page Object Model (POM) | Design pattern for maintainable test code |

---

## Project Structure

```
Calley_Team/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── BaseClass.java          # WebDriver setup/teardown, loads config
│   │   │   └── pompages/
│   │   │       ├── RegistrationPage.java   # Registration, OTP verification, plan selection
│   │   │       ├── LoginPage.java          # Login + popup dismissal
│   │   │       ├── AgentPage.java          # Add Agent flow
│   │   │       ├── CSVUploadPage.java      # Call List > Power Import
│   │   │       └── DashboardPage.java      # Post-login dashboard checks
│   │   └── resources/
│   │       ├── data.properties         # URLs, credentials, test data (data-driven)
│   │       └── sample_leads.csv        # Sample CSV for Power Import testing
│   └── test/
│       └── java/
│           ├── RegistrationTest.java   # End-to-end registration test
│           └── FullSetupTest.java      # Login -> Add Agent -> CSV Upload (ordered)
├── pom.xml
├── testng.xml
└── README.md
```

---

## Getting Started

### Prerequisites
- Java JDK 8+
- Maven
- Google Chrome installed
- Eclipse (or any Java IDE) with the TestNG plugin

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/Rahulchandeshware/Calley-Teams-Automation.git
   ```
2. Open the project in Eclipse as a Maven project.
3. Update `src/main/resources/data.properties` with valid test credentials (see [Configuration](#configuration) below).
4. Run `mvn clean install` to pull dependencies.

### Running the tests
- Right-click `testng.xml` → **Run As → TestNG Suite**, or
- Run via Maven: `mvn test`

---

## Configuration

All test data and URLs live in `data.properties` — nothing is hardcoded in the Java classes:

```properties
registration.url=https://app.getcalley.com/registration.aspx
login.url=https://app.getcalley.com/Login.aspx

reg.fullName=Your Name
reg.email=your.real.email@example.com
reg.whatsapp=<a real WhatsApp-enabled number>
reg.password=YourPassword123

login.email=<email of an account that completed registration>
login.password=YourPassword123

agent.name=Test Agent
agent.mobile=9123456780
agent.email=testagent@example.com
agent.password=Agent@12345
agent.lead=

csv.listName=AutomationTestList
csv.filePath=src/main/resources/sample_leads.csv
```

> **Note:** `reg.whatsapp` must be a real, WhatsApp-enabled number, since Calley sends an actual OTP to it during registration (see [Known Limitations](#known-limitations) below).

---

## Known Limitations

Two steps in this flow **cannot be fully automated** and require manual interaction during the test run, by design — not as a framework gap:

| Step | Why it can't be automated |
|---|---|
| **reCAPTCHA** ("I'm not a robot") | Google's reCAPTCHA is specifically built to detect and block automated/Selenium-driven browsers. The framework pauses execution (`waitForManualRecaptcha()`) so a human can click it manually. |
| **WhatsApp OTP verification** | The OTP is sent to a real WhatsApp number and has no programmatic API access. The framework pauses (`waitForManualOtpEntry()`) so a human can read the message and enter the code. |

Both are the industry-standard workaround for automating flows with 2FA/CAPTCHA — pausing for manual input rather than attempting to bypass security controls.

---

## Design Notes

- **Page Object Model:** each screen (Registration, Login, Agent, CSV Upload) has its own class containing only locators + actions. Test classes never reference a `By` locator directly.
- **Popup handling:** several SweetAlert/Bootstrap popups (OTP-sent alert, "Agent Added Successfully", "Are you sure?" Power Import confirmation, app-install promo, notification subscription prompt) are dismissed automatically within page methods, so they never block subsequent test steps.
- **Data-driven:** all test data lives in `data.properties`, keeping test logic and test data cleanly separated.
- **Independent tests:** each `@Test` method gets a fresh browser session via `BaseClass`'s `@BeforeMethod`/`@AfterMethod`, trading a little speed for easier debugging when a single step fails.

---

## Author

Rahul Chandeshware
