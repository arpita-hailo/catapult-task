package steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import steps.properties.TestProperties;

import static io.restassured.RestAssured.given;

public class CucumberHooks {

    @Autowired
    private TestProperties testProperties;

    @Before
    public void setUp() {
        RestAssured.requestSpecification = given().baseUri(testProperties.getUrl())
                        .contentType(ContentType.JSON);

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}
