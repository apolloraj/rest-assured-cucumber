# rest-assured-cucumber

A test project for running the rest-assured api testing using cucumber and Maven

# Instructions

Clone the repo:

Git:
```
$ git clone git@github.com:testdouble/java-cucumber-example.git
```
Or download a ZIP of master [manually](https://github.com/testdouble/java-cucumber-example/archive/master.zip) and expand the contents someplace on your system

## Prerequisites

In order to run the test in the local machine the below dependencies should be installed to your system.
1. Java 8
2. Maven
3. Intellij or eclipse

(TODO after testing on Windows)

## Use Maven

Open a command window and run:
    For the first run :
        
    mvn clean install
    
    mvn test

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `MyRunner`
class tells JUnit to kick off Cucumber.

## Verify installation

The test generated a html reports and the report can be checked in the target folder
/target/cucumber-reports/report.html
