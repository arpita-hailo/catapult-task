package steps.dao.auther;

import lombok.Builder;
import lombok.Data;
import steps.dao.NullableStringFieldUpdateOperationsInput;
import steps.dao.StringFieldUpdateOperationsInput;

@Data
@Builder
public class AutherUpdateWithoutPostsInput {

    private StringFieldUpdateOperationsInput id;
    private NullableStringFieldUpdateOperationsInput name;
}
