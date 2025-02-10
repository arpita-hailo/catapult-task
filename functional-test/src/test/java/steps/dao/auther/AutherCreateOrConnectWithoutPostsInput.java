package steps.dao.auther;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AutherCreateOrConnectWithoutPostsInput {
    @JsonInclude
    private AutherWhereUniqueInput where;

    @JsonInclude
    private AutherCreateWithoutPostsInput create;

}
