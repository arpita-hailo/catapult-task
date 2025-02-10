package scenarios;


import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import util.TestConstants;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static util.LoadTestSLA.globalPause;

public class BookMutationScenario {

    static ChainBuilder createBooksMutation = exec(
      http("mutation create a book")
              .post(TestConstants.URL.value)
              .header("Content-Type","application/json")
              .body(RawFileBody("query/create_book.json"))
              .check(status().is(200))
              .check(jsonPath("$.data.createBook.id").saveAs("createdBookId"))
    );

    static ChainBuilder updateBookMutation = exec(
            http("mutation update a book")
                    .post(TestConstants.URL.value)
                    .header("Content-Type","application/json")
                    .body(ElFileBody("query/update_book.json"))
                    .check(status().is(200))
                    .check(jsonPath("$.data.updateBook"))
    );

    static ChainBuilder removeBookMutation = exec(
            http("mutation remove a book")
                    .post(TestConstants.URL.value)
                    .header("Content-Type","application/json")
                    .body(ElFileBody("query/remove_book.json"))
                    .check(status().is(200))
                    .check(jsonPath("$.data.removeBook"))
    );


    public static ScenarioBuilder bookMutation = scenario("Mutation books").forever().on(
            exec(createBooksMutation).pause(Duration.ofSeconds(globalPause)),
            exec(updateBookMutation).pause(Duration.ofSeconds(globalPause)),
            exec(removeBookMutation).pause(Duration.ofSeconds(globalPause))
    );
}
