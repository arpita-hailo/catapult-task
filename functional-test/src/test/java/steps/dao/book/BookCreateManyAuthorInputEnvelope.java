package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookCreateManyAuthorInputEnvelope {
    private List<BookCreateManyAuthorInput> data;
    private boolean skipDuplicates;
}
