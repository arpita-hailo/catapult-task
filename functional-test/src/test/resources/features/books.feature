Feature: Validate Books API

  Scenario: Validate bookAll query
    When I fetch all books
    Then response should have at least 1 book

  Scenario Outline: Validate bookById query
    Given A book is created with
      | title   | authorMode   | authorName   | content   | published   |
      | <title> | <authorMode> | <authorName> | <content> | <published> |
    When I fetch a book by its id
    Then the response should contain the Book with
      | title   | authorMode   | authorName   | content   | published   |
      | <title> | <authorMode> | <authorName> | <content> | <published> |

    Examples:
      | title          | authorMode | authorName | content        | published |
      | My latest book | create     | Mr Amazing | my-content.txt | true      |

  Scenario Outline: Validate createBook Mutation
    When I create a Book with
      | title   | authorMode   | authorName   |
      | <title> | <authorMode> | <authorName> |
    Then the create response should contain the Book with
      | title   | authorName   |
      | <title> | <authorName> |

    Examples:
      | title                               | authorMode | authorName     |
      | My latest book                      |            |                |
      #this one fails bug-1
      | My latest book with new author      | create     | created author |
      #this one fails bug-1
      | My latest book with existing author | connect    | connect author |

  Scenario: Validate error thrown for duplicate createBook Mutation
    Given A book is created with
      | title            |
      | My original book |
    When I use the same id to create a Book with
      | title              |
      | My book first copy |
    Then the service should return an error

  Scenario Outline: Validate updateBook Mutation
    Given A book is created with
      | title   | content   | published   |
      | <title> | <content> | <published> |
    When I update the following fields of the book
      | title          | content          | published          |
      | <updatedTitle> | <updatedContent> | <updatedPublished> |
    Then the update response should contain the Book with
      | title          | content          | published          |
      | <updatedTitle> | <updatedContent> | <updatedPublished> |

    Examples:
      | title          | updatedTitle        | content        | updatedContent         | published | updatedPublished |
      | My latest book | Updated latest book | my-content.txt | my-updated-content.txt | false     | true             |

  Scenario Outline: Validate removeBook mutation
    Given A book is created with
      | title   |
      | <title> |
    When I remove a book by its id
    Then the remove response should contain the Book with
      | title   |
      | <title> |

    Examples:
      | title          |
      | My latest book |