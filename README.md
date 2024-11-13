# E-commerce Automation Testing Project

## Overview
This project is an automation suite for testing the functionality of an e-commerce website, specifically targeting [https://www.alexandnova.com/](https://www.alexandnova.com/). It leverages Selenium WebDriver to automate interactions with the site’s user interface and validate core e-commerce features. The testing suite is designed to ensure a smooth and error-free user experience, covering a range of scenarios including navigation, product selection, cart management, and checkout.

## Features
- **Automated Test Scenarios**: Includes automated test cases for critical e-commerce functionalities:
  - **User Authentication**: Tests for login and registration flows.
  - **Product Browsing**: Validates product page navigation and filtering.
  - **Shopping Cart Management**: Tests for adding, updating, and removing items in the cart.
  - **Checkout Process**: Automates and verifies the checkout workflow, including payment and order confirmation.
- **Modular Test Suite**: Organized test cases located in `src/test/java/org/example`, enabling easy maintenance and scalability for future tests.
- **Data-Driven Testing**: Tests are designed to use different data inputs, ensuring the site functions as expected under various conditions.

## Test Environment
- **Isolated Testing Environment**: The testing suite runs against a controlled staging environment of the e-commerce site to prevent data interference with live customer interactions.
- **Environment Variables for Configuration**: Login credentials and other sensitive data are stored using environment variables, ensuring secure access and configuration flexibility.

## Technologies Used
- **Java**: Primary language for scripting automated test cases.
- **Selenium WebDriver**: For browser automation and UI interaction.
- **JUnit**: Framework used for structuring test cases and managing assertions.
- **Maven**: Manages project dependencies and builds.
- **Environment Variables**: For securely storing sensitive data like login credentials.
