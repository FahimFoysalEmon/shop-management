package com.shop.shopmanagementbackend.categories;

import com.shop.shopmanagementbackend.categories.utils.CategoryEnums;
import com.shop.shopmanagementbackend.common.exception.DataMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category saveCategory(CategoryEnums name) {
        if (categoryRepo.existsByName(name)) {
            throw new DataMismatchException("Duplicate Category name");
        }

        Category category = new Category();
        category.setName(name);

        return categoryRepo.save(category);
    }

    public List<Category> fetchCategories() {
        return categoryRepo.findAll();
    }
}
