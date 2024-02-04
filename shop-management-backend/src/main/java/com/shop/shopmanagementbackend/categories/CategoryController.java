package com.shop.shopmanagementbackend.categories;

import com.shop.shopmanagementbackend.categories.utils.CategoryEndPointUtils;
import com.shop.shopmanagementbackend.categories.utils.CategoryEnums;
import com.shop.shopmanagementbackend.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = CategoryEndPointUtils.SAVE_CATEGORY)
    public ResponseEntity<ApiResponse> saveCategory(@RequestParam CategoryEnums name) {
        return new ResponseEntity<>(new ApiResponse("Category created", categoryService.saveCategory(name)), HttpStatus.CREATED);
    }



    @PostMapping(value = CategoryEndPointUtils.FETCH_CATEGORIES)
    public ResponseEntity<ApiResponse> fetchCategories() {
        return new ResponseEntity<>(new ApiResponse("Category created", categoryService.fetchCategories()), HttpStatus.CREATED);
    }
}
