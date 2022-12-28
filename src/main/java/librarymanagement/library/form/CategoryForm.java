package librarymanagement.library.form;

import javax.validation.constraints.Size;

public class CategoryForm {
    @Size(max=255)
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    

    
}
