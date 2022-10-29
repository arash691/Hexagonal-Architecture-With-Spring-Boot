@CreatePatient
Feature: Can I create new patient?

  Scenario: Creating a new patient
    Given I provide all required data to create a patient
    Then A new patient is created