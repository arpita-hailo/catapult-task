package steps;

import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.springframework.stereotype.Component;
import steps.dao.auther.Auther;
import steps.dao.auther.AutherCreateInput;
import steps.dao.book.Book;
import steps.dao.book.BookCreateInput;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ScenarioScope
public class TestContext {
    private Response response;
    private RequestSpecification req;
    private List<Book> bookList;
    private List<BookCreateInput> testbook = new ArrayList<>();
    private AutherCreateInput testauther;
}
