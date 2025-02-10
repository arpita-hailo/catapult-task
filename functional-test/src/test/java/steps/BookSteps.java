package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import steps.dao.book.*;
import steps.util.GraphQLQuery;
import steps.util.GraphQLQueryExecutor;
import steps.util.QueryStringBook;
import steps.util.TestDataSetupUtil;

import static org.hamcrest.Matchers.*;

public class BookSteps {

    @Autowired
    private TestContext testContext;

    @Autowired
    private GraphQLQuery graphQLQuery;

    @Autowired
    private TestDataSetupUtil testDataSetupUtil;

    @Autowired
    private GraphQLQueryExecutor graphQLQueryExecutor;


    @Given("A book is created with")
    public void aBookIsCreatedWith(BookCreateInput book) {
        testDataSetupUtil.createRandomBookForTest(book);

    }

    @When("I fetch all books")
    public void iFetchAllBooks() {
        graphQLQuery.setQuery(QueryStringBook.BOOK_ALL_QUERY.value);
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I fetch a book by its id")
    public void iFetchABookByItsId() {
        graphQLQuery.setQuery(QueryStringBook.BOOK_BY_ID_QUERY.value);
        graphQLQuery.setVariables(BookWhereUniqueInput.builder().id(testContext.getTestbook().getFirst().getId()).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I create a Book with")
    public void iCreateABookWith(BookCreateInput book) {
        graphQLQuery.setQuery(QueryStringBook.CREATE_BOOK.value);
        graphQLQuery.setVariables(BookCreateInputWrapper.builder().bookCreateInput(book).build());
        graphQLQueryExecutor.execute(graphQLQuery);

    }

    @When("I use the same id to create a Book with")
    public void iUseTheSameIdToCreateABookWith(BookCreateInput book) {
        graphQLQuery.setQuery(QueryStringBook.CREATE_BOOK.value);
        book.setId(testContext.getTestbook().getFirst().getId());
        graphQLQuery.setVariables(BookCreateInputWrapper.builder().bookCreateInput(book).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use the same author id to create a Book with")
    public void iUseTheSameAuthorIdToCreateABookWith(BookCreateInput book) {
        graphQLQuery.setQuery(QueryStringBook.CREATE_BOOK.value);
        book.getAuthor().getCreate().setId(testContext.getTestauther().getId());
        graphQLQuery.setVariables(BookCreateInputWrapper.builder().bookCreateInput(book).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I use an invalid author id to create a Book with")
    public void iUseAnInvalidAuthorIdToCreateABookWith(BookCreateInput book) {
        graphQLQuery.setQuery(QueryStringBook.CREATE_BOOK.value);
        book.getAuthor().getConnect().setId("000-000-000-000");
        graphQLQuery.setVariables(BookCreateInputWrapper.builder().bookCreateInput(book).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I update the following fields of the book")
    public void iUpdateABookWith(BookUpdateWithoutAuthorInput book) {
        graphQLQuery.setQuery(QueryStringBook.UPDATE_BOOK.value);
        graphQLQuery.setVariables(BookUpdateWithoutAuthorInputWrapper.builder().bookUpdateWithoutAuthorInput(book).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @When("I remove a book by its id")
    public void iRemoveABookByIts() {
        graphQLQuery.setQuery(QueryStringBook.REMOVE_BOOK.value);
        graphQLQuery.setVariables(BookWhereUniqueInput.builder().id(testContext.getTestbook().getFirst().getId()).build());
        graphQLQueryExecutor.execute(graphQLQuery);
    }

    @Then("response should have at least {int} book")
    public void responseShouldHaveAtLeastBook(int minBookCount) {
        testContext.getResponse().then().body("data.bookAll",hasSize(greaterThanOrEqualTo(minBookCount)));
    }

    @Then("the response should contain the Book with")
    public void theResponseShouldContainTheBookWith(Book book) {
        testContext.getResponse().then().body("data.book.title",equalTo(book.getTitle()));
        testContext.getResponse().then().body("data.book.content",equalTo(book.getContent()));
        testContext.getResponse().then().body("data.book.published",equalTo(book.isPublished()));
        testContext.getResponse().then().body("data.book.author.name",equalTo(book.getAuthor().getName()));
    }

    @Then("the create response should contain the Book with")
    public void theCreateResponseShouldContainTheBookWith(Book book) {
        testContext.getResponse().then().body("data.createBook.title",equalTo(book.getTitle()));
        testContext.getResponse().then().body("data.createBook.author.name",equalTo(book.getAuthor().getName()));
    }

    @Then("the update response should contain the Book with")
    public void theUpdateResponseShouldContainTheBookWith(Book book) {
        testContext.getResponse().then().body("data.updateBook.title",equalTo(book.getTitle()));
        testContext.getResponse().then().body("data.updateBook.content",equalTo(book.getContent()));
        testContext.getResponse().then().body("data.updateBook.published",equalTo(book.isPublished()));
    }

    @Then("the remove response should contain the Book with")
    public void theRemoveResponseShouldContainTheBookWith(Book book) {
        testContext.getResponse().then().body("data.removeBook.title",equalTo(book.getTitle()));
    }

    @Then("the service should return an error")
    public void theServiceShouldReturnAnError() {
        testContext.getResponse().then().body("errors",notNullValue());
    }
}



