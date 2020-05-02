Feature: post employee by employee name

  @test
  Scenario Outline: User create a employee
    Given create a stub for a create employee "<apiName>"
    Then send a create employee request "<apiName>" with the details "<id>" "<employeeName>" "<firstName>" "<lastName>" "<email>" "<password>" "<phone>" "<employeeStatus>"
    When the POST status code is "<statusCode>"

  Examples:
  |apiName |statusCode|id|employeeName  |firstName|lastName|email       |password |phone  |employeeStatus|
  |employee|200       | 1|shabnamfathima|shabnam  |fathima |xx@gmail.com|password1|0123456|0             |

#  @test
#  Scenario Outline: User create a employee with a arraylist
#    Given create a stub for a create employee "<apiName>"
#    Then generate a list of employee list
#      |id|employeeName   |firstName|lastName|email       |password |phone  |employeeStatus|
#      | 1|shabnamfathima |shabnam  |fathima |xx@gmail.com|password1|0123456|0             |
#      | 2|shabnamfathima2|shabnam  |fathima2|xx@gmail.com|password2|0123457|0             |
#    When the POST status code is "<statusCode>"
#
#    Examples:
#      |apiName |statusCode|
#      |employee|200       |
