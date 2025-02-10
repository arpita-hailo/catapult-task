Feature: Validate book and author

  Scenario Outline: Validate books are listed for the author correctly when the author was created or linked during the creation of the book
    When I create a Book with
      | title   | authorMode   | authorName   |
      | <title> | <authorMode> | <authorName> |
    And I fetch an author by its id
    Then the response should contain the author with
      | name         | bookTitle |
      | <authorName> | <title>   |

    Examples:
      | title                               | authorMode | authorName     |
      | My latest book with existing author | connect    | connect author |

  Scenario Outline: Validate author is listed for the book correctly when the book was created or linked during the creation of the author
    When I create an author with
      | name   | bookMode   | bookTitle   |
      | <name> | <bookMode> | <bookTitle> |
    When I fetch a book by its id
    Then the response should contain the Book with
      | title       | authorName |
      | <bookTitle> | <name>     |

    Examples:
      | name                  | bookMode | bookTitle      |
      | Author with connected | connect  | book connected |

  Scenario Outline: Validate error thrown while trying to create an existing author during createBook Mutation
    Given An author is created with
      | name         |
      | <authorName> |
    When I use the same author id to create a Book with
      | title   | authorMode   | authorName   |
      | <title> | <authorMode> | <authorName> |
    Then the service should return an error

    Examples:
      | title                | authorMode | authorName      |
      | My wrong author book | create     | existing author |

  Scenario Outline: Validate error thrown while trying to connect an invalid author during createBook Mutation
    When I use an invalid author id to create a Book with
      | title   | authorMode   | authorName   |
      | <title> | <authorMode> | <authorName> |
    Then the service should return an error

    Examples:
      | title                | authorMode | authorName     |
      | My wrong author book | connect    | connect author |

  Scenario Outline: Validate error thrown while trying to create an existing book during createAuthor Mutation
    Given A book is created with
      | title       |
      | <bookTitle> |
    When I use the same book id to create an author with
      | name   | bookMode   | bookTitle   |
      | <name> | <bookMode> | <bookTitle> |
    Then the service should return an error

    Examples:
      | name                | bookMode | bookTitle    |
      | Author with created | create   | book created |

  Scenario Outline: Validate error thrown while trying to connect an invalid book during createAuthor Mutation
    When I use an invalid book id to create an Author with
      | name   | bookMode   | bookTitle   |
      | <name> | <bookMode> | <bookTitle> |
    Then the service should return an error

    Examples:
      | name                  | bookMode | bookTitle      |
      | Author with connected | connect  | book connected |
#      | Author with created many | createMany | book_created1,book_created2 |

  Scenario Outline: Validate error thrown while trying to create existing books during createAuthor Mutation
    Given A book is created with
      | title         |
      | book_created1 |
    And A book is created with
      | title         |
      | book_created2 |
    When I use the same book ids to create an author with
      | name   | bookMode   | bookTitle   |
      | <name> | <bookMode> | <bookTitle> |
    Then the service should return an error

    Examples:
      | name                     | bookMode   | bookTitle                   |
      | Author with created many | createMany | book_created1,book_created2 |

  Scenario Outline: Validate success while trying to create existing books during createAuthor Mutation with skipDuplicates set to true
    Given A book is created with
      | title                |
      | book_created_already |
    When I use the same book ids but with skipDuplicates to create an author with
      | name   | bookMode   | bookTitle   |
      | <name> | <bookMode> | <bookTitle> |
    Then the create response should contain the author with
      | name   | bookTitle |
      | <name> | book_new  |

    Examples:
      | name                     | bookMode   | bookTitle                     |
#      this is failing because of bug-2; skipDuplicates flag is working as expected
      | Author with created many | createMany | book_created_already,book_new |


  Scenario Outline: Validate author update should be reflected in the queryBook response
    Given A book is created with
      | title   | authorMode   | authorName   |
      | <title> | <authorMode> | <authorName> |
    And I update an author with
      | name          |
      | <updatedname> |
    And I fetch a book by its id
    Then the response should contain the Book with
      | title   | authorMode   | authorName    |
      | <title> | <authorMode> | <updatedname> |

    Examples:
      | title                          | authorMode | authorName     | updatedname           |
      | My latest book with new author | connect    | created author | Updated latest author |

  Scenario Outline: Validate author delete should be reflected in the queryBook response
    Given A book is created with
      | title   | authorMode   | authorName   |
      | <title> | <authorMode> | <authorName> |
    And I remove an author by its id
    And I fetch a book by its id
    Then the response should contain the Book with
      | title   | authorMode   | authorName |
      | <title> | <authorMode> |            |

    Examples:
      | title                          | authorMode | authorName     |
      | My latest book with new author | connect    | created author |


