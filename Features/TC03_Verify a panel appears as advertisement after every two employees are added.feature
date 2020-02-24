Feature: Verification of advertisement panel

  Scenario: Verify Advertisement panel appears after every two employee records are added 
    When I create more than two employee records
    Then Advertisement panel should appear on creating third record
    
    
    