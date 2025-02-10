package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import steps.dao.auther.AutherCreateNestedOneWithoutPostsInput;

@Data
@Builder
public class BookCreateWithoutAuthorInput {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean published;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean delete;
}
