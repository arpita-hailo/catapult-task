package steps.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NullableStringFieldUpdateOperationsInput {
    private String set;
}
