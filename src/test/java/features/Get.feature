Feature: To Test Get functionality using Rest Assured

  Scenario: Get API Happy path scenario
    Given url "https://gorest.co.in/public/v2/users"
    When userID is "8159075"
    Then status should be 200

