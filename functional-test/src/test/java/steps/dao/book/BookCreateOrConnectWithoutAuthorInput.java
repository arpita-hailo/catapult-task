package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookCreateOrConnectWithoutAuthorInput {
    private BookWhereUniqueInput where;
    private BookCreateWithoutAuthorInput create;
}
