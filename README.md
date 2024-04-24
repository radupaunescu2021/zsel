## Java Project with TestNG and Selenium WebDriver
This project demonstrates the use of TestNG and Selenium WebDriver for automated web testing for www.saucedemo.com

It uses POM design pattern creating separate classes for each page

## Prerequisites
**Java JDK 11 or higher.**

Maven (for dependency management and running the tests).

A compatible web browser (**Google Chrome**) for Selenium tests.

An IDE such as **IntelliJ IDEA** (optional, but recommended).

## Setup
Clone the Repository:

Clone this repository to your local machine or download the source code.

 ``` git clone https://github.com/radupaunescu2021/zsel.git ```

Install Dependencies:

Open the project in your IDE or navigate to the project directory in the command line.

Run ``` mvn clean install  ``` to install the required dependencies (**TestNG, Selenium, WebDriverManager**).

Web Browser Setup for Selenium:

Ensure you have the web browser installed (**Google Chrome**).

WebDriverManager will handle the driver setup for the browser.

## Running the Tests

**RUN from cmd line:**

Run ``` mvn test ``` to execute the unit tests written with TestNG.

These tests include checking Pascal's Triangle calculations and validating balanced parentheses functionality: **AppTest.java**

Selenium WebDriver Tests: **SeleniumTest.java**

The Selenium tests are configured to run as part of the TestNG test suite.


**RUN from Intellij IDE**

**right click on SeleniumTest--->click run SeleniumTest**


