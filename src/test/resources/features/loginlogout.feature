Feature: Test to validate the login and logout features

  @test
  Scenario Outline: an /employee/login api to logs employee into the system
    Given create a stub for a "login" request with "<apiName>" "<userName>" "<userPassword>"
    When a users makes a "login" request with a "<apiName>" "<userName>" "<userPassword>"
    Then the status code "<statusCode>" is returned

  Examples:
  |apiName       |userName|userPassword|statusCode|
  |employee/login|user1   |password1   |200       |
  |employee/login|user2   |password2   |200       |

  @test
  Scenario Outline: an /employee/logout api to Logs out current logged in employee session
    Given create a stub for a "logout" request with "<apiName>" "" ""
    When a users makes a "logout" request with a "<apiName>" "" ""
    Then the status code "<statusCode>" is returned

    Examples:
      |apiName        |statusCode|
      |employee/logout|200       |
