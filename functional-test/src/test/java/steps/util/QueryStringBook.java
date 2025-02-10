package steps.util;

public enum QueryStringBook {
    BOOK_ALL_QUERY("""
            {  bookAll{
                    id
                    title
                    author{
                      id
                      name
                    }
                  }
                  }
            """),

    BOOK_BY_ID_QUERY("""
            query book($id:String){
                  book(id:{id:$id}){
                 id
                 title
                 content
                 published
                 author{
                   id
                   name
                 }
               }
               }
            """),
    CREATE_BOOK("""
            mutation createBook($bookCreateInput:BookCreateInput!){
              createBook(createBookInput:$bookCreateInput){
                id
                title
                author{
                    name
                }
              }
            }
            """),
    UPDATE_BOOK("""
            mutation updateBook($bookUpdateWithoutAuthorInput:BookUpdateWithoutAuthorInput!){
              updateBook(updateBookInput:$bookUpdateWithoutAuthorInput){
                id
                title
                content
                published
              }
            }
            """),
    REMOVE_BOOK("""
            mutation removeBook($id:String){
                  removeBook(id:{id:$id}){
                 id
                 title
                 author{
                   id
                   name
                 }
               }
               }
            """);


    public final String value;

    private QueryStringBook(String value) {
        this.value = value;
    }

}
