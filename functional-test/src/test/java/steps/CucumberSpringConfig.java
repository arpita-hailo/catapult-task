package steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import steps.config.TestConfig;

@CucumberContextConfiguration
@SpringBootTest(classes ={TestConfig.class})
public class CucumberSpringConfig {
}
