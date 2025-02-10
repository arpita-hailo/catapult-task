package steps.inputs;

import io.cucumber.java.DataTableType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import steps.TestContext;
import steps.dao.NullableBoolFieldUpdateOperationsInput;
import steps.dao.NullableStringFieldUpdateOperationsInput;
import steps.dao.StringFieldUpdateOperationsInput;
import steps.dao.auther.*;
import steps.dao.book.*;
import steps.util.TestDataSetupUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static steps.util.FileUtils.getBookContentFromFile;

public class InputArguments {
    @Autowired
    private TestContext testContext;

    @Autowired
    private TestDataSetupUtil testDataSetupUtil;

    @DataTableType
    public Book bookArgument(Map<String,String> dataTable){
        return Book.builder()
                .title(dataTable.get("title"))
                .content(getBookContentFromFile(dataTable.get("content")))
                .published(Boolean.parseBoolean(dataTable.get("published")))
                .author(Auther.builder()
                        .name(dataTable.get("authorName"))
                        .build())
                .build();
    }

    @DataTableType
    public BookCreateInput createBookArgument(Map<String,String> dataTable){
        AutherCreateNestedOneWithoutPostsInput author = null;
        if("create".equalsIgnoreCase(dataTable.get("authorMode"))){
            author = AutherCreateNestedOneWithoutPostsInput.builder()
                    .create(AutherCreateWithoutPostsInput.builder().
                            name(dataTable.get("authorName")).
                            build())
                    .build();
        } else if ("connect".equalsIgnoreCase(dataTable.get("authorMode"))) {
            // creating an author to connect
            testDataSetupUtil.createRandomAuthorForTest(AutherCreateInput.builder().
                    name(dataTable.get("authorName")).
                    build());

            author = AutherCreateNestedOneWithoutPostsInput.builder()
                    .connect(AutherWhereUniqueInput.builder().
                            id(testContext.getTestauther().getId()).
                            build())
                    .build();
        }
        return BookCreateInput.builder()
                .title(dataTable.get("title"))
                .author(author)
                .content(getBookContentFromFile(dataTable.get("content")))
                .published(Boolean.parseBoolean(dataTable.get("published")))
                .build();
    }

    @DataTableType
    public BookUpdateWithoutAuthorInput updateBookArgument(Map<String,String> dataTable){
        return BookUpdateWithoutAuthorInput.builder()
                .id(StringFieldUpdateOperationsInput.builder().set(testContext.getTestbook().getFirst().getId()).build())
                .title(StringFieldUpdateOperationsInput.builder().set(dataTable.get("title")).build())
                .content(NullableStringFieldUpdateOperationsInput.builder().set(getBookContentFromFile(dataTable.get("content"))).build())
                .published(NullableBoolFieldUpdateOperationsInput.builder().set(Boolean.parseBoolean(dataTable.get("published"))).build())
                .build();
    }

    @DataTableType
    public Auther authorArgument(Map<String,String> dataTable){
        List<Book> books = new ArrayList<>();

        if(dataTable.get("bookTitle") != null) {
            String[] bookTitles = dataTable.get("bookTitle").split(",");
            for(int i=0;i<bookTitles.length;i++){
                books.add(Book.builder()
                        .title(bookTitles[i])
                        .content(getBookContentFromFile(safeSplitOrNull(dataTable.get("bookContent"),",",i)))
                        .published(Boolean.parseBoolean(safeSplitOrNull(dataTable.get("bookPublished"),",",i)))
                        .build());
            }
        }

        return Auther.builder()
                .name(dataTable.get("name"))
                .posts(books)
                .build();
    }

    @DataTableType
    public AutherCreateInput authorCreateArgument(Map<String,String> dataTable){
        BookCreateNestedManyWithoutAuthorInput book = null;
        if("create".equalsIgnoreCase(dataTable.get("bookMode"))){
            List<BookCreateWithoutAuthorInput> bookCreateWithoutAuthorInputList = new ArrayList<>();

            String[] bookTitles = dataTable.get("bookTitle").split(",");
            for(int i=0;i<bookTitles.length;i++){
                bookCreateWithoutAuthorInputList.add(BookCreateWithoutAuthorInput.builder()
                        .title(bookTitles[i])
                        .content(getBookContentFromFile(safeSplitOrNull(dataTable.get("bookContent"),",",i)))
                        .published(Boolean.parseBoolean(safeSplitOrNull(dataTable.get("bookPublished"),",",i)))
                        .build());
            }

            book = BookCreateNestedManyWithoutAuthorInput.builder()
                    .create(bookCreateWithoutAuthorInputList)
                    .build();
        } else if ("connect".equalsIgnoreCase(dataTable.get("bookMode"))) {
            // creating a book to connect
            List<BookWhereUniqueInput> bookWhereUniqueInputList = new ArrayList<>();

            String[] bookTitles = StringUtils.split(dataTable.get("bookTitle"),",");

            for(int i = 0; i<bookTitles.length; i++){
                testDataSetupUtil.createRandomBookForTest(BookCreateInput.builder().
                        title(bookTitles[i]).
                        content(getBookContentFromFile(safeSplitOrNull(dataTable.get("bookContent"),",",i))).
                        published(Boolean.parseBoolean(safeSplitOrNull(dataTable.get("bookPublished"),",",i))).
                        build());

                bookWhereUniqueInputList.add(BookWhereUniqueInput.builder()
                        .id(testContext.getTestbook().get(i).getId())
                        .build());
            }

            book = BookCreateNestedManyWithoutAuthorInput.builder()
                    .connect(bookWhereUniqueInputList)
                    .build();
        } else if ("createMany".equalsIgnoreCase(dataTable.get("bookMode"))){

            List<BookCreateManyAuthorInput> bookCreateManyAuthorInputList = new ArrayList<>();
            for(String bookTitle:dataTable.get("bookTitle").split(",")){
                bookCreateManyAuthorInputList.add(BookCreateManyAuthorInput.builder()
                        .title(bookTitle)
                        .build());
            }
            book = BookCreateNestedManyWithoutAuthorInput.builder()
                    .createMany(BookCreateManyAuthorInputEnvelope.builder()
                            .data(bookCreateManyAuthorInputList)
                            .build())
                    .build();
        }
        return AutherCreateInput.builder()
                .name(dataTable.get("name"))
                .posts(book)
                .build();
    }

    @DataTableType
    public AutherUpdateWithoutPostsInput authorUpdateArgument(Map<String,String> dataTable){
        return AutherUpdateWithoutPostsInput.builder()
                .id(StringFieldUpdateOperationsInput.builder().set(testContext.getTestauther().getId()).build())
                .name(NullableStringFieldUpdateOperationsInput.builder().set(dataTable.get("name")).build())
                .build();
    }

    private String safeSplitOrNull(String str,String del,int index){
        if(str != null){
            return StringUtils.split(str,del)[index];
        }else{
            return str;
        }
    }

}
