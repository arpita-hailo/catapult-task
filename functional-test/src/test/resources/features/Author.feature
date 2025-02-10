Feature: Validate Author API


  Scenario: Validate authorAll query
    When I fetch all authors
    Then response should have at least 1 author

  Scenario Outline: Validate authorById query
    Given An author is created with
      | name   | bookMode   | bookTitle   | bookContent   | bookPublished   |
      | <name> | <bookMode> | <bookTitle> | <bookContent> | <bookPublished> |
    When I fetch an author by its id
    Then the response should contain the author with
      | name   | bookMode   | bookTitle   | bookContent   | bookPublished   |
      | <name> | <bookMode> | <bookTitle> | <bookContent> | <bookPublished> |

    Examples:
      | name             | bookMode | bookTitle                 | bookContent                     | bookPublished |
      | My latest author | create   | Book for fun              | my-content.txt                  | true          |
      | My latest author | create   | Book for fun,another book | my-content.txt,my-content-1.txt | true,false    |

  Scenario Outline: Validate createAuthor Mutation
    When I create an author with
      | name   | bookMode   | bookTitle   |
      | <name> | <bookMode> | <bookTitle> |
    Then the create response should contain the author with
      | name   | bookTitle   |
      | <name> | <bookTitle> |

    Examples:
      | name                     | bookMode   | bookTitle                   |
      | Mr Author                |            |                             |
      #this one fails bug-2
      | Author with created      | create     | book created                |
      #this one fails bug-2
      | Author with connected    | connect    | book connected              |
      | Author with created many | createMany | book_created1,book_created2 |

  Scenario: Validate error thrown for duplicate createAuthor Mutation
    Given An author is created with
      | name        |
      | Mr Original |
    When I use the same id to create an Author with
      | name        |
      | Mr Imposter |
    Then the service should return an error

  Scenario Outline: Validate updateAuthor Mutation
    Given An author is created with
      | name   |
      | <name> |
    When I update an author with
      | name          |
      | <updatedname> |
    Then the update response should contain the author with
      | name          |
      | <updatedname> |

    Examples:
      | name             | updatedname           |
      | My latest author | Updated latest author |

  Scenario Outline: Validate removeAuthor mutation
    Given An author is created with
      | name   |
      | <name> |
    When I remove an author by its id
    Then the remove response should contain the author with
      | name   |
      | <name> |

    Examples:
      | name             |
      | My latest author |