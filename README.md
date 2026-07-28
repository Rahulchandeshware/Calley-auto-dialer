# Calley Teams Automation

Selenium + TestNG automation framework for the Calley Team Account Setup flow on `app.getcalley.com`, built using the Page Object Model (POM) design pattern with Java and Maven.

---

## 📌 Project Description

This project automates four core user journeys within the Calley Teams account setup process:

- **User Registration** — including WhatsApp OTP verification and free trial plan activation
- **User Login**
- **Add Agent**
- **CSV Upload** via Power Import

**Core Objectives:**
- Build a maintainable, data-driven automation framework using the Page Object Model
- Reduce manual regression effort for critical onboarding flows (registration → login → agent setup → lead upload)
- Keep test data and locators fully separated from test logic for easy maintenance
- Handle real-world blockers (reCAPTCHA, WhatsApp OTP) gracefully with manual-pause checkpoints instead of brittle workarounds

---

## 🛠️ Tech Stack Used

| Tool | Purpose |
|------|---------|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test framework & assertions |
| Maven | Build & dependency management |
| WebDriverManager | Auto-manages ChromeDriver binaries |
| Page Object Model (POM) | Design pattern for maintainable test code |

---

## 🚀 Setup & Installation

### Prerequisites
- Java JDK 8+
- Maven
- Google Chrome installed
- Eclipse (or any Java IDE) with the TestNG plugin

### Steps

1. **Clone the repository**
   \`\`\`bash
   git clone https://github.com/Rahulchandeshware/Calley-Teams-Automation.git
   \`\`\`

2. **Open the project in Eclipse** as a Maven project.

3. **Update configuration** — edit `src/main/resources/data.properties` with valid test credentials:
   \`\`\`properties
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
   \`\`\`
   > **Note:** `reg.whatsapp` must be a real, WhatsApp-enabled number, since Calley sends an actual OTP to it during registration.

4. **Install dependencies**
   \`\`\`bash
   mvn clean install
   \`\`\`

5. **Run the tests**
   - Right-click `testng.xml` → *Run As* → *TestNG Suite*, **or**
   - Run via Maven:
     \`\`\`bash
     mvn test
     \`\`\`

---

## ✨ Key Features & Highlights

- **Page Object Model architecture** — each screen (Registration, Login, Agent, CSV Upload) has its own class containing only locators and actions. Test classes never reference a `By` locator directly.
- **Automatic popup handling** — SweetAlert/Bootstrap popups (OTP-sent alert, "Agent Added Successfully", Power Import confirmation, app-install promo, notification prompt) are dismissed automatically within page methods so they never block subsequent steps.
- **Data-driven design** — all test data lives in `data.properties`, keeping test logic and test data cleanly separated.
- **Independent, isolated tests** — each `@Test` method gets a fresh browser session via `BaseClass`'s `@BeforeMethod`/`@AfterMethod`, trading a little speed for easier debugging.
- **Smart handling of manual security checkpoints** — reCAPTCHA and WhatsApp OTP verification are paused for human input (`waitForManualRecaptcha()`, `waitForManualOtpEntry()`) rather than attempting unreliable bypasses — the industry-standard approach for automating 2FA/CAPTCHA-protected flows.

### Project Structure
\`\`\`
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
\`\`\`

### Known Limitations

| Step | Why it can't be automated |
|------|---------------------------|
| reCAPTCHA ("I'm not a robot") | Google's reCAPTCHA is specifically built to detect and block automated/Selenium-driven browsers. The framework pauses execution so a human can click it manually. |
| WhatsApp OTP verification | The OTP is sent to a real WhatsApp number with no programmatic API access. The framework pauses so a human can read the message and enter the code. |

---

## Author

**Rahul Chandeshware**
GitHub: [github.com/Rahulchandeshware](https://github.com/Rahulchandeshware)
