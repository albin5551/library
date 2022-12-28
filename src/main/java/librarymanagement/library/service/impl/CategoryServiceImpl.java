package librarymanagement.library.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Category;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.CategoryForm;
import librarymanagement.library.repository.CategoryRepository;
import librarymanagement.library.service.CategoryService;
import librarymanagement.library.view.CategoryListView;

@Service

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryListView add(CategoryForm form) {
        return new CategoryListView(categoryRepository.save(new Category(form)));
    }

    @Override
    public CategoryListView get(Integer categoryId) throws NotFoundException {
        return categoryRepository.findById(categoryId)
                .map((category) -> {
                    return new CategoryListView(category);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public CategoryListView update(Integer categoryId, CategoryForm form) throws NotFoundException {
        return categoryRepository.findById(categoryId)
                .map((category) -> {
                    return new CategoryListView(categoryRepository.save(category.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    // @Override
    // @Transactional
    // public void delete(Integer categoryId) throws NotFoundException {
    //     categoryRepository.delete(
    //             categoryRepository.findById(categoryId).orElseThrow()

    //     );
    // }
    @Override
    @Transactional
    public CategoryListView delete(Integer categoryId) throws NotFoundException {

        Category category=categoryRepository.findById(categoryId).get();
        return new CategoryListView(categoryRepository.save(category.delete()));
    }
}
