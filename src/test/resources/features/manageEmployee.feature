Feature: Scenarios on managing the employees with path parameters

  @test
  Scenario Outline: User makes a GET call to get the employee details for the given employee name
    Given create a stub for "<method>" request with an "<endpointName>" "<EmployeeName>"
    Then a users makes a "<method>" request with an "<endpointName>" "<EmployeeName>"
    When the status code is 200
    And response includes the name "employeename" and value "<EmployeeName>"

  Examples:
  |EmployeeName  |endpointName|method|
  |shabnamfathima|employee    |GET   |

  @test
  Scenario Outline: User makes a PUT call to update the employee details for the given employee name
    Given create a stub for "<method>" request with an "<endpointName>" "<EmployeeName>"
    Then a users makes a "<method>" request with an "<endpointName>" "<EmployeeName>"
    When the status code is 200

    Examples:
    |EmployeeName  |endpointName|method|
    |shabnamfathima|employee    |PUT   |

  @test
  Scenario Outline: User makes a DELETE call to delete an employee details
    Given create a stub for "<method>" request with an "<endpointName>" "<EmployeeName>"
    Then a users makes a "<method>" request with an "<endpointName>" "<EmployeeName>"
    When the status code is 200

    Examples:
    |EmployeeName  |endpointName|method|
    |shabnamfathima|employee    |DELETE|

