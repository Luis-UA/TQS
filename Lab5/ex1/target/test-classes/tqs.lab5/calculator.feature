Feature: Add and subtract
  Background: a calculator
    Given a calculator I just turned on


  Scenario Outline: Add two numbers
    When I add <a> and <b>
    Then I get <c>

  Examples: Single digits
    | a | b | c  |
    | 5 | 3 | 8  |
    | 2 | 4 | 6  |
    | 1 | 2 | 3  |
    | 6 | 7 | 13 |

  Scenario: Subtract a number from another
    When I subtract 2 from 11
    Then I get 9
