Feature: User CRUD

  Scenario: Create a new user
    When I send a POST request to /users with body:
    """
      {
        "uuid": "b8bd9278-b164-49a4-ad50-77df7ace8cec",
        "username": "jperez",
        "name": "Juan Perez",
        "email": "jperez@email.com",
        "password": "xxxXXxX"
      }
    """
    Then I should receive an empty body
    And status code should be 201

  Scenario: Get existing user
    Given I send a POST request to /users with body:
    """
      {
        "uuid": "b8bd9278-b164-49a4-ad50-77df7ace8cec",
        "username": "jperez",
        "name": "Juan Perez",
        "email": "jperez@email.com",
        "password": "xxxXXxX"
      }
    """
    When I send a GET request to /users/b8bd9278-b164-49a4-ad50-77df7ace8cec
    Then I should receive the following body:
    """
      {
        "uuid": "b8bd9278-b164-49a4-ad50-77df7ace8cec",
        "username": "jperez",
        "name": "Juan Perez",
        "email": "jperez@email.com"
      }
    """
    And status code should be 200

  Scenario: Delete existing user
    Given I send a POST request to /users with body:
    """
      {
        "uuid": "b8bd9278-b164-49a4-ad50-77df7ace8cec",
        "username": "jperez",
        "name": "Juan Perez",
        "email": "jperez@email.com",
        "password": "xxxXXxX"
      }
    """
    When I send a DELETE request to /users/b8bd9278-b164-49a4-ad50-77df7ace8cec
    And I send a GET request to /users/b8bd9278-b164-49a4-ad50-77df7ace8cec
    Then status code should be 404