package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import steps.dao.auther.*;
import steps.dao.book.Book;
import steps.dao.book.BookCreateManyAuthorInput;
import steps.dao.book.BookCreateManyAuthorInputEnvelope;
import steps.util.GraphQLQuery;
import steps.util.GraphQLQueryExecutor;
import steps.util.QueryStringAuthor;
import steps.util.TestDataSetupUtil;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthorSteps {

    @Autowired
    private TestContext testContext;

    @Autowired
    private GraphQLQuery graphQLQuery;

    @Autowired
    private TestDataSetupUtil testDataSetupUtil;

    @Autowired
    private GraphQLQueryExecutor graphQLQueryExecutor;


    @Given("An author is created with")
    public void anAuthorIsCreatedWith(AutherCreateInput author) {
        testDataSetupUtil.createRandomAuthorForTest(author);

    }

    @When("I fetch all authors")
    public void iFetchAllAuthors() {
        graphQLQuery.setQuery(QueryStringAuthor.AUTHOR_ALL_QUERY.value);
        graphQLQueryExecutor.execute(graphQLQuery);
    }


    @When("I fetch an author by its id")
    public void iFetchAnAuthorByItsId() {
        graphQLQuery.setQuery(QueryStringAuthor.AUTHOR_BY_ID_QUERY.value);
        graphQLQuery.setVariables(AutherWhereUniqueInput.builder().id(testContext.getTestauther().getId()).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I create an author with")
    public void iCreateAnAuthorWith(AutherCreateInput author) {
        graphQLQuery.setQuery(QueryStringAuthor.CREATE_AUTHER.value);
        graphQLQuery.setVariables(AutherCreateInputWrapper.builder().autherCreateInput(author).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use the same id to create an Author with")
    public void iUseTheSameIdToCreateAnAuthorWith(AutherCreateInput author) {
        graphQLQuery.setQuery(QueryStringAuthor.CREATE_AUTHER.value);
        author.setId(testContext.getTestauther().getId());
        graphQLQuery.setVariables(AutherCreateInputWrapper.builder().autherCreateInput(author).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use the same book id to create an author with")
    public void iUseTheSameBookIdToCreateAnAuthorWith(AutherCreateInput author) {
        graphQLQuery.setQuery(QueryStringAuthor.CREATE_AUTHER.value);
        author.getPosts().getCreate().getFirst().setId(testContext.getTestbook().getFirst().getId());
        graphQLQuery.setVariables(AutherCreateInputWrapper.builder().autherCreateInput(author).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use an invalid book id to create an Author with")
    public void iUseAnInvalidBookIdToCreateAnAuthorWith(AutherCreateInput author) {
        graphQLQuery.setQuery(QueryStringAuthor.CREATE_AUTHER.value);
        author.getPosts().getConnect().getFirst().setId("000-000-000-000");
        graphQLQuery.setVariables(AutherCreateInputWrapper.builder().autherCreateInput(author).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use the same book ids to create an author with")
    public void iUseTheSameBookIdsToCreateAnAuthorWith(AutherCreateInput author) {
        graphQLQuery.setQuery(QueryStringAuthor.CREATE_AUTHER.value);
        List<BookCreateManyAuthorInput> posts = author.getPosts().getCreateMany().getData();
        for(int i=0;i<testContext.getTestbook().size();i++){
            posts.get(i).setId(testContext.getTestbook().get(i).getId());
        }
        graphQLQuery.setVariables(AutherCreateInputWrapper.builder().autherCreateInput(author).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use the same book ids but with skipDuplicates to create an author with")
    public void iUseTheSameBookIdsButWithSkipDuplicatesToCreateAnAuthorWith(AutherCreateInput author) {
        author.getPosts().getCreateMany().setSkipDuplicates(true);
        iUseTheSameBookIdsToCreateAnAuthorWith(author);
    }

    @When("I update an author with")
    public void iUpdateAnAuthorWith(AutherUpdateWithoutPostsInput author) {
        graphQLQuery.setQuery(QueryStringAuthor.UPDATE_AUTHOR.value);
        graphQLQuery.setVariables(AutherUpdateWithoutPostsInputWrapper.builder().autherUpdateWithoutPostsInput(author).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I remove an author by its id")
    public void iRemoveAnAuthorByIts() {
        graphQLQuery.setQuery(QueryStringAuthor.REMOVE_AUTHOR.value);
        graphQLQuery.setVariables(AutherWhereUniqueInput.builder().id(testContext.getTestauther().getId()).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @Then("response should have at least {int} author")
    public void responseShouldHaveAtLeastAuthor(int minAuthorCount) {
        testContext.getResponse().then().body("data.autherAll",hasSize(greaterThanOrEqualTo(minAuthorCount)));
    }


    @Then("the response should contain the author with")
    public void theResponseShouldContainTheAuthorWith(Auther auther) {
        testContext.getResponse().then().body("data.auther.name",equalTo(auther.getName()));
        List<Book> posts = auther.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            Book post = posts.get(i);
            testContext.getResponse().then().body("data.auther.posts[" +i +"].title", equalTo(post.getTitle()));
            testContext.getResponse().then().body("data.auther.posts[" +i +"].content", equalTo(post.getContent()));
            testContext.getResponse().then().body("data.auther.posts[" +i +"].published", equalTo(post.isPublished()));
        }
    }

    @Then("the create response should contain the author with")
    public void theCreateResponseShouldContainTheAuthorWith(Auther auther) {
        testContext.getResponse().then().body("data.createAuther.name",equalTo(auther.getName()));
        List<Book> posts = auther.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            Book post = posts.get(i);
            testContext.getResponse().then().body("data.createAuther.posts[" +i +"].title", equalTo(post.getTitle()));
        }
    }

    @Then("the update response should contain the author with")
    public void theUpdateResponseShouldContainTheAuthorWith(Auther auther) {
        testContext.getResponse().then().body("data.updateAuther.name",equalTo(auther.getName()));
    }

    @Then("the remove response should contain the author with")
    public void theRemoveResponseShouldContainTheAuthorWith(Auther auther) {
        testContext.getResponse().then().body("data.removeAuther.name",equalTo(auther.getName()));
    }
}



