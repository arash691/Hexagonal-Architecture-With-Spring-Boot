@CreateDoctor
Feature: Can I create new doctor?

  Scenario: Creating a new doctor
    Given I provide all required data to create a doctor
    Then A new doctor is created