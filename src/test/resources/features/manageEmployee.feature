Feature: Scenarios on managing the employees with path parameters

  @test
  Scenario Outline: User makes a GET call to get the employee details for the given employee name
    Given create a stub for "<method>" request with an "<endpointName>" "<EmployeeName>"
    When a users makes a "<method>" request with an "<endpointName>" "<EmployeeName>"
    Then validate the status code is "<statusCode>"
    And response includes the name "employeename" and value "<EmployeeName>"

  Examples:
  |EmployeeName  |endpointName|method|statusCode|
  |shabnamfathima|employee    |GET   |200       |

  @test
  Scenario Outline: User makes a PUT call to update the employee details for the given employee name
    Given create a stub for "<method>" request with an "<endpointName>" "<EmployeeName>"
    When a users makes a "<method>" request with an "<endpointName>" "<EmployeeName>"
    Then validate the status code is "<statusCode>"

    Examples:
    |EmployeeName  |endpointName|method|statusCode|
    |shabnamfathima|employee    |PUT   |200       |

  @test
  Scenario Outline: User makes a DELETE call to delete an employee details
    Given create a stub for "<method>" request with an "<endpointName>" "<EmployeeName>"
    When a users makes a "<method>" request with an "<endpointName>" "<EmployeeName>"
    Then validate the status code is "<statusCode>"

    Examples:
    |EmployeeName  |endpointName|method|statusCode|
    |shabnamfathima|employee    |DELETE|200       |

