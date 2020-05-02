Feature: post employee by employee name

  @test
  Scenario Outline: User create a employee
    Given create a stub for a create employee "<apiName>"
    Then send a create employee request "<apiName>" with the details "<id>" "<employeeName>" "<firstName>" "<lastName>" "<email>" "<password>" "<phone>" "<employeeStatus>"
    When the POST status code is 200

  Examples:
  |apiName |id|employeeName  |firstName|lastName|email       |password |phone  |employeeStatus|
  |employee| 1|shabnamfathima|shabnam  |fathima |xx@gmail.com|password1|0123456|0             |
