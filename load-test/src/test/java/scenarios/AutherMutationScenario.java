package scenarios;


import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import util.TestConstants;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static util.LoadTestSLA.globalPause;

public class AutherMutationScenario {

    static ChainBuilder createAutherMutation = exec(
      http("mutation create an auther")
              .post(TestConstants.URL.value)
              .header("Content-Type","application/json")
              .body(RawFileBody("query/create_auther.json"))
              .check(status().is(200))
              .check(jsonPath("$.data.createAuther.id").saveAs("createdAutherId"))
    );

    static ChainBuilder updateAutherMutation = exec(
            http("mutation update an auther")
                    .post(TestConstants.URL.value)
                    .header("Content-Type","application/json")
                    .body(ElFileBody("query/update_auther.json"))
                    .check(status().is(200))
                    .check(jsonPath("$.data.updateAuther"))
    );

    static ChainBuilder removeAutherMutation = exec(
            http("mutation remove an auther")
                    .post(TestConstants.URL.value)
                    .header("Content-Type","application/json")
                    .body(ElFileBody("query/remove_auther.json"))
                    .check(status().is(200))
                    .check(jsonPath("$.data.removeAuther"))
    );


    public static ScenarioBuilder autherMutation = scenario("Mutation authers").forever().on(
            exec(createAutherMutation).pause(Duration.ofSeconds(globalPause)),
            exec(updateAutherMutation).pause(Duration.ofSeconds(globalPause)),
            exec(removeAutherMutation).pause(Duration.ofSeconds(globalPause))
    );
}
