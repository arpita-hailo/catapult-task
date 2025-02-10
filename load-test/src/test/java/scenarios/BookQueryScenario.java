package scenarios;


import util.TestConstants;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static util.LoadTestSLA.globalPause;

import io.gatling.javaapi.core.*;

import java.time.Duration;

public class BookQueryScenario {

    static ChainBuilder allBooksQuery = exec(
      http("query all books")
              .post(TestConstants.URL.value)
              .header("Content-Type","application/json")
              .body(RawFileBody("query/all_book.json"))
              .check(status().is(200))
              .check(jsonPath("$.data.bookAll[0].id").saveAs("bookId"))
    );

    static ChainBuilder bookByIdQuery = exec(
            http("query book by id")
                    .post(TestConstants.URL.value)
                    .header("Content-Type","application/json")
                    .body(ElFileBody("query/by_id_book.json"))
                    .check(status().is(200))
                    .check(jsonPath("$.data.book"))
    );

    public static ScenarioBuilder bookQuery = scenario("Query books").forever().on(
            exec(allBooksQuery).pause(Duration.ofSeconds(globalPause)),
            exec(bookByIdQuery).pause(Duration.ofSeconds(globalPause))
            );

}
