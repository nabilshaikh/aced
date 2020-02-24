Feature: Delete an employee

  Scenario: Employee details should no longer be present on grid on performing delete operation
    When I search for newly created employee as below and click on Delete Employee button coloured in red
    						| First Name |Last Name|  Job Title   | Current Project |
    						|    Hello   |  World  | Sr Developer |     Consumer    |
    Then Employee record should get deleted successfully
    
    