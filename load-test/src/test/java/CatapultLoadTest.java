import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.core.*;

import java.time.Duration;

import static scenarios.AutherMutationScenario.autherMutation;
import static scenarios.AutherQueryScenario.autherQuery;
import static scenarios.BookMutationScenario.bookMutation;
import static scenarios.BookQueryScenario.bookQuery;
import static util.LoadTestSLA.*;

public class CatapultLoadTest extends Simulation {

    @Override
    public void before() {
        System.out.println("Simulation is about to start ....");
    }

    @Override
    public void after() {
        System.out.println("Simulation is finished ....");
    }

    {
        setUp(
                bookQuery.injectOpen(rampUsers(1).during(5)),
                autherQuery.injectOpen(rampUsers(1).during(5)),
                bookMutation.injectOpen(rampUsers(2).during(5)),
                autherMutation.injectOpen(rampUsers(1).during(5))
        ).maxDuration(Duration.ofMinutes(testDuration))
                .constantPauses()
//                .throttle(
//                        throttleSteps
//                )
                .assertions(assertions);
    }
}
