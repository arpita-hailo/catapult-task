package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import steps.dao.auther.Auther;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean published;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Auther author;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String authorId;

}
