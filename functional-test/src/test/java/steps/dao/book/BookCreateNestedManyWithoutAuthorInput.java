package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookCreateNestedManyWithoutAuthorInput {
    private List<BookCreateWithoutAuthorInput> create;
    private List<BookCreateOrConnectWithoutAuthorInput> connectOrCreate;
    private BookCreateManyAuthorInputEnvelope createMany;
    private List<BookWhereUniqueInput> connect;
}
