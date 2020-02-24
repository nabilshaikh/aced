Feature: Verification of advertisement panel after adding & removing multiple employee records

  Scenario: Verify panel should always appear after every two employee details
    When I add new 9 employee records to make the total count to 11
    And delete few records in order of 1, 2 & 10
    Then All three records should get deleted successfully and verify the advertisement panel stays in correct position
    
    
    