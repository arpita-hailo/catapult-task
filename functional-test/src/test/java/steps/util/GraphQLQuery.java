package steps.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GraphQLQuery {
    private String query;
    private Object variables;
}
