package librarymanagement.library.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import librarymanagement.library.form.CategoryForm;

@Entity
public class Category {
    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Category() {

    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Category(CategoryForm form) {
        this.categoryName = form.getCategoryName();

        this.status = Status.ACTIVE.value;

        Date dt = new Date();
        this.createDate = dt;

    }

    public Category update(CategoryForm form){
        this.categoryName=form.getCategoryName();

        Date dt = new Date();
        this.updateDate=dt;
        return this;
    }

    public Category delete() {
        this.status = Status.INACTIVE.value;
        return this;
     }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Category)) {
            return false;
        }
        return Objects.equals(categoryId, ((Category) object).categoryId);
    }

    @Override
    public String toString() {
        return "librarymanagement.library.entity.Category[ categoryId=" + categoryId + " ]";
    }

}
