Feature: Verify toast alert

  Scenario Outline: Appropriate message should appear on alert on submitting incomplete form
    Given Open the application and create new employee record 
    When I populate all the mandatory fields "<firstname>", "<lastname>" & "<jobtitle>" leaving behind Current Project field"
    Then Appropriate message should appear on alert
    
    Examples:
      |firstname|lastname|  jobtitle  |
    	|   Sam   |  Sung  |Sr Developer|