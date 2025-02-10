package steps.util;

import org.springframework.beans.factory.annotation.Autowired;
import steps.TestContext;

import static io.restassured.RestAssured.given;

public class GraphQLQueryExecutor {

    @Autowired
    private TestContext testContext;

    public void execute(GraphQLQuery graphQLQuery){
        testContext.setResponse(given().body(graphQLQuery)
                .when().post());
        testContext.getResponse()
                .then().statusCode(200);
    }
}
