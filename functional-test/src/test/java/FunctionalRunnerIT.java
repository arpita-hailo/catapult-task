import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"steps"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/Cucumber.json"})
public class FunctionalRunnerIT {
}
