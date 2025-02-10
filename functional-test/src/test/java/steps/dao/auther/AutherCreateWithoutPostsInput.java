package steps.dao.auther;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutherCreateWithoutPostsInput {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String name;
}
