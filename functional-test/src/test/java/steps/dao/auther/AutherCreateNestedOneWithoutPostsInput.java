package steps.dao.auther;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutherCreateNestedOneWithoutPostsInput {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AutherCreateWithoutPostsInput create;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AutherCreateOrConnectWithoutPostsInput connectOrCreate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AutherWhereUniqueInput connect;
}
