package steps.config;

import io.cucumber.spring.ScenarioScope;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import steps.TestContext;
import steps.properties.TestProperties;
import steps.util.GraphQLQuery;
import steps.util.GraphQLQueryExecutor;
import steps.util.TestDataSetupUtil;

@Configuration
@EnableConfigurationProperties({TestProperties.class})
public class TestConfig {

    @Bean
    @ScenarioScope
    TestContext testContext(){
        return  new TestContext();
    }

    @Bean
    GraphQLQuery graphQLQuery(){return new GraphQLQuery();}

    @Bean
    GraphQLQueryExecutor graphQLQueryExecutor(){return new GraphQLQueryExecutor();}

    @Bean
    TestDataSetupUtil setupUtil(){return new TestDataSetupUtil();}


}
