package steps.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StringFieldUpdateOperationsInput {
    private String set;
}
