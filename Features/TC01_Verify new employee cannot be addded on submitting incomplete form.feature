Feature: Create new employee with incomplete details

  Scenario Outline: User try to add a new employee with incomplete information
    Given Open the application
    When I click on Add Employee button from Home page
    And Populate all the mandatory fields "<firstname>", "<lastname>" & "<jobtitle>" leaving behind Current Project field"
    And Click on Create Employee button
    Then Error message should appear and new employee should not get created
    
    Examples:
      |firstname|lastname|  jobtitle  |
    	|   Sam   |  Sung  |Sr Developer|