package scenarios;


import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import util.TestConstants;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static util.LoadTestSLA.globalPause;

public class AutherQueryScenario {

    static ChainBuilder allAuthersQuery = exec(
      http("query all authers")
              .post(TestConstants.URL.value)
              .header("Content-Type","application/json")
              .body(RawFileBody("query/all_auther.json"))
              .check(status().is(200))
              .check(jsonPath("$.data.autherAll[0].id").saveAs("autherId"))
    );

    static ChainBuilder autherByIdQuery = exec(
            http("query auther by id")
                    .post(TestConstants.URL.value)
                    .header("Content-Type","application/json")
                    .body(ElFileBody("query/by_id_auther.json"))
                    .check(status().is(200))
                    .check(jsonPath("$.data.auther"))
    );


    public static ScenarioBuilder autherQuery = scenario("Query authers").forever().on(
            exec(allAuthersQuery).pause(Duration.ofSeconds(globalPause)),
            exec(autherByIdQuery).pause(Duration.ofSeconds(globalPause))
    );
}
