package steps.util;

public enum QueryStringAuthor {
    AUTHOR_ALL_QUERY("""
            {  autherAll{
                    id
                    name
                  }
                  }
            """),

    AUTHOR_BY_ID_QUERY("""
            query auther($id:String){
                  auther(id:{id:$id}){
                 id
                 name
                 posts{
                    title
                    content
                    published
                }
               }
               }
            """),
    CREATE_AUTHER("""
            mutation createAuther($autherCreateInput:AutherCreateInput!){
              createAuther(createAutherInput:$autherCreateInput){
                id
                name
                posts{
                    title
                }
              }
            }
            """),
    UPDATE_AUTHOR("""
            mutation updateAuther($autherUpdateWithoutPostsInput:AutherUpdateWithoutPostsInput!){
              updateAuther(updateAutherInput:$autherUpdateWithoutPostsInput){
                id
                name
              }
            }
            """),
    REMOVE_AUTHOR("""
            mutation removeAuther($id:String){
                  removeAuther(id:{id:$id}){
                 id
                 name
               }
               }
            """);


    public final String value;

    private QueryStringAuthor(String value) {
        this.value = value;
    }

}
