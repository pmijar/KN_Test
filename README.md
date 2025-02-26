# Project Overview

This project is designed to automate the testing of the iPhone purchasing process on the Apple Store website. The test script simulates the process of selecting and purchasing an iPhone by following these steps:

1. Navigate to the Apple Store website.
2. Select the iPhone category.
3. Choose the iPhone version and model.
4. Configure the various options on the purchase page.
5. Update the nearest store location with availability using an externally controlled variable.
6. Complete the checkout process.

---

# Project Setup

This project is built using **Maven**, which automatically handles the dependencies for the project.

## Pre-requisites

Ensure the following tools are installed and properly configured on your machine:

- **JDK 21**: Install Java Development Kit 21 and set the `JAVA_HOME` environment variable.
- **Maven**: Install Maven and set the `MAVEN_HOME` environment variable.
- **Git**: Install Git to clone this repository.
- **Chrome Browser**: Ensure Chrome is installed for browser automation.
- **IDE**: Use IntelliJ IDEA or Eclipse to open and execute the project.

## Setup Steps

1. Create an empty directory on your local machine.
2. Clone this repository into the directory.
3. Wait for Maven to automatically download all the required dependencies.
4. Navigate to the project directory (`src/main/java/org/example/App.java`).
5. Run the `runTest()` method to execute the test.

---

# Test Data

The test data for product selection (such as iPhone model, color, capacity, etc.) is managed via a `testdata.json` file.

- **Location**: `/src/main/java/Utils/testdata.json`
- **Sample `testdata.json` content**:

```json
[
  {
    "VER": "16",
    "MODEL": "Plus",
    "SCREENSIZE": "6_1inch",
    "COLOR": "black",
    "CAPACITY": "128gb",
    "ADDRESS": "Albany, NY"
  }
]
```

# Next Steps
* Page Object Model (POM): Extend the project by implementing the Page Object Model (POM) design pattern for better maintainability.
* Framework Enhancements: Add additional utilities such as:
  * Reporting
  * Logging
  * Screenshot capture features
* Structure the project to allow test execution via the `mvn` command line, facilitating easy integration into the CI/CD pipeline. 