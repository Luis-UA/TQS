Feature: Book Search
  Background: in a library
    Given a book with the title 'It', written by 'Stephen King', published in 15-09-1986
    And another book with the title 'To Kill a Mockingbird', written by 'Harper Lee', published in 11-07-1960
    And another book with the title '1984', written by 'George Orwell', published in 08-06-1949
    And another book with the title 'The Shining', written by 'Stephen King', published in 28-01-1977
    And another book with the title 'The Old Man and the Sea', written by 'Ernest Hemingway', published in 01-09-1952
    And another book with the title 'The Hobbit', written by 'J. R. R. Tolkien', published in 21-09-1937

  Scenario: Search books by Author
    When the customer searches for books by 'Stephen King'
    Then 2 books should have been found
    And Book 1 should have the title 'It'
    And Book 2 should have the title 'The Shining'

  Scenario: Search books by publication year
    When the customer searches for books published between 1930 and 1950
    Then 2 books should have been found
    And Book 1 should have the title '1984'
    And Book 2 should have the title 'The Hobbit'

  Scenario: Search book by similar title
    When the customer searches for books with the title 'The'
    Then 3 book should have been found
    And Book 1 should have the title 'The Shining'
    And Book 2 should have the title 'The Old Man and the Sea'
    And Book 3 should have the title 'The Hobbit'
