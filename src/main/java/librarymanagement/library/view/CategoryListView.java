package librarymanagement.library.view;

import java.util.Date;

import librarymanagement.library.entity.Category;
import librarymanagement.library.json.Json;

public class CategoryListView {
    private final int categoryId;
    private final String categoryName;
    private final byte status;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;


    public CategoryListView(Category category){
        this.categoryId=category.getCategoryId();
        this.categoryName=category.getCategoryName();
        this.status=category.getStatus();
        this.createDate=category.getCreateDate();
        this.updateDate=category.getUpdateDate();
    }

    
    public int getCategoryId() {
        return categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public byte getStatus() {
        return status;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }

    
}
