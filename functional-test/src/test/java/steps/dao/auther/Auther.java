package steps.dao.auther;

import lombok.Builder;
import lombok.Data;
import steps.dao.book.Book;

import java.util.List;

@Builder
@Data
public class Auther {
    private String Id;
    private String name;
    private List<Book> posts;
    private AutherCount _count;
}
