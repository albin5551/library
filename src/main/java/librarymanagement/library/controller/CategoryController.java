package librarymanagement.library.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import librarymanagement.library.entity.Category;
import librarymanagement.library.form.CategoryForm;
import librarymanagement.library.service.CategoryService;
import librarymanagement.library.view.CategoryListView;


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
 
    @GetMapping
    public Collection<Category> list() {
       return categoryService.list();
    }
 
    @PostMapping
    public CategoryListView add(@Valid @RequestBody CategoryForm form) {
 
       // System.out.println("++++++++++++++++++++++"+form.getCategoryName());
       // System.out.println("?????????????????????"+form.getCategoryType());
       return categoryService.add(form);
    }
 
    @GetMapping("/{categoryId}")
    public CategoryListView get(@PathVariable("categoryId") Integer categoryId) {
       return categoryService.get(categoryId);
    }
 
    @PutMapping("/{categoryId}")
    public CategoryListView update(
          @PathVariable("categoryId") Integer categoryId,
          @Valid @RequestBody CategoryForm form) {
       return categoryService.update(categoryId, form);
    }
 
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable("categoryId") Integer categoryId) {
       categoryService.delete(categoryId);
       // System.out.println("Delete Success");
    }
 
    
}
