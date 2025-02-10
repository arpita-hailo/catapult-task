package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCreateManyAuthorInput {
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
