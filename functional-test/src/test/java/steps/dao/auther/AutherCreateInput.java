package steps.dao.auther;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import steps.dao.book.BookCreateNestedManyWithoutAuthorInput;

@Builder
@Data
public class AutherCreateInput {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BookCreateNestedManyWithoutAuthorInput posts;

}
