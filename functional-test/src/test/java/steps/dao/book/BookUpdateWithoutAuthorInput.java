package steps.dao.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import steps.dao.NullableBoolFieldUpdateOperationsInput;
import steps.dao.NullableStringFieldUpdateOperationsInput;
import steps.dao.StringFieldUpdateOperationsInput;

@Data
@Builder
public class BookUpdateWithoutAuthorInput {
    private StringFieldUpdateOperationsInput id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StringFieldUpdateOperationsInput title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NullableStringFieldUpdateOperationsInput content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NullableBoolFieldUpdateOperationsInput published;
}
