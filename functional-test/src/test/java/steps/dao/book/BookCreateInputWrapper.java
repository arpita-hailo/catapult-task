package steps.dao.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCreateInputWrapper {
    private BookCreateInput bookCreateInput;
}
