package librarymanagement.library.service;

import java.util.Collection;

import librarymanagement.library.entity.Category;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.CategoryForm;
import librarymanagement.library.view.CategoryListView;

public interface CategoryService {
    Collection<Category> list();

    CategoryListView add(CategoryForm form);

    CategoryListView get(Integer categoryId) throws NotFoundException;

    CategoryListView update(Integer categoryId, CategoryForm form) throws NotFoundException;

    CategoryListView delete(Integer categoryId) throws NotFoundException;
    
}
