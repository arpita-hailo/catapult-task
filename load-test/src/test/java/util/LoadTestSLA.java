package util;

import io.gatling.javaapi.core.Assertion;
import io.gatling.javaapi.core.ThrottleStep;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.details;

public class LoadTestSLA {
    public static int globalPause = 6;
    public static int testDuration = 15;
    static int querySLA = 1000;
    static int mutationSLA = 2000;
    static double errorTolerance = 1.0;

    public static Assertion[] assertions = {
            details("query all books").responseTime().percentile3().lte(querySLA),
            details("query book by id").responseTime().percentile3().lte(querySLA),
            details("query all authers").responseTime().percentile3().lte(querySLA),
            details("query auther by id").responseTime().percentile3().lte(querySLA),
            details("mutation create a book").responseTime().percentile3().lte(mutationSLA),
            details("mutation update a book").responseTime().percentile3().lte(mutationSLA),
            details("mutation remove a book").responseTime().percentile3().lte(mutationSLA),
            details("mutation update an auther").responseTime().percentile3().lte(mutationSLA),
            details("mutation update an auther").responseTime().percentile3().lte(mutationSLA),
            details("mutation update an auther").responseTime().percentile3().lte(mutationSLA),
            global().failedRequests().percent().lte(errorTolerance)
    };

    public static ThrottleStep[] throttleSteps = {
            jumpToRps(2),
            holdFor(Duration.ofMinutes(1)),
            jumpToRps(1),
            holdFor(Duration.ofMinutes(14))
    };

}
