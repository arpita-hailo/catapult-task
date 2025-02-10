package steps.dao.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookUpdateWithoutAuthorInputWrapper {
    private BookUpdateWithoutAuthorInput bookUpdateWithoutAuthorInput;
}
