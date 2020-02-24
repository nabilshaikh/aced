Feature: Test to check the presence of newly created employee on reopening the browser 

  Scenario Outline: Newly created employee record should be retained permanently
    When I open the application and create new employee
    And save the details by entering "<firstname>","<lastname>","<jobtitle>" and "<currentproject>"
    And close the browser
    And reopen the browser
    Then newly created employee "<firstname>" should be present
    
    Examples:
    	|firstname|lastname|  jobtitle  |currentproject|
    	|  hello  |  world |Sr Developer| Professional |
    