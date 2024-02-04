package com.shop.shopmanagementbackend.products;

import com.shop.shopmanagementbackend.categories.Category;
import com.shop.shopmanagementbackend.categories.CategoryRepo;
import com.shop.shopmanagementbackend.common.exception.DataMismatchException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {


    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    @Transactional
    public Product saveProduct(String categoryId, Product product) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new DataMismatchException("Category with this id not found"));

        if (category.getProductList().stream()
                .anyMatch(existingProduct -> existingProduct.getName().equals(product.getName()))) {
            throw new DataMismatchException("Product already exists");
        }

        product.setCategory(category);
        category.getProductList().add(product);

        categoryRepo.save(category);

        return productRepo.save(product);
    }

    public List<Product> fetchProducts() {
        return productRepo.findAll();
    }
}
