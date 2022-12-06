package librarymanagement.library.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookForm {

    @NotBlank
    @Size(max = 255)
    private String bookName;

    @NotBlank
    @Size(max = 255)
    private String bookAuthor;

    private Integer stock;


    
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    


    
    
}
