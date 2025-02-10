package steps.util;

import org.springframework.beans.factory.annotation.Autowired;
import steps.TestContext;
import steps.dao.auther.Auther;
import steps.dao.auther.AutherCreateInput;
import steps.dao.auther.AutherCreateInputWrapper;
import steps.dao.book.Book;
import steps.dao.book.BookCreateInput;
import steps.dao.book.BookCreateInputWrapper;

import static io.restassured.RestAssured.given;

public class TestDataSetupUtil {
    @Autowired
    private GraphQLQuery graphQLQuery;

    @Autowired
    private TestContext testContext;



    public void createRandomBookForTest(BookCreateInput testBook){

        graphQLQuery.setQuery(QueryStringBook.CREATE_BOOK.value);
        graphQLQuery.setVariables(BookCreateInputWrapper.builder().bookCreateInput(
                BookCreateInput.builder().
                        title(testBook.getTitle()).
                        author(testBook.getAuthor()).
                        content(testBook.getContent()).
                        published(testBook.isPublished()).
                        build()
        ).build());
        String bookId = given().body(graphQLQuery)
                .when().post().then().extract().path("data.createBook.id");
        testBook.setId(bookId);
        testContext.getTestbook().add(testBook);
    }

    public void createRandomAuthorForTest(AutherCreateInput testAuthor){

        graphQLQuery.setQuery(QueryStringAuthor.CREATE_AUTHER.value);
        graphQLQuery.setVariables(AutherCreateInputWrapper.builder().autherCreateInput(
                AutherCreateInput.builder().
                        name(testAuthor.getName()).
                        posts(testAuthor.getPosts()).
                        build()
        ).build());
        String authorId = given().body(graphQLQuery)
                .when().post().then().extract().path("data.createAuther.id");
        testAuthor.setId(authorId);
        testContext.setTestauther(testAuthor);
    }
}
