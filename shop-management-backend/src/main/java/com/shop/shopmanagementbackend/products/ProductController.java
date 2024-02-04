package com.shop.shopmanagementbackend.products;

import com.shop.shopmanagementbackend.categories.utils.CategoryEnums;
import com.shop.shopmanagementbackend.common.ApiResponse;
import com.shop.shopmanagementbackend.products.utils.ProductEndPointUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = ProductEndPointUtils.SAVE_PRODUCT)
    public ResponseEntity<ApiResponse> saveProduct(@PathVariable String categoryId,
                                                   @RequestBody Product product) {
        return new ResponseEntity<>(new ApiResponse("Product created", productService.saveProduct(categoryId,product)), HttpStatus.CREATED);
    }


    @GetMapping(value = ProductEndPointUtils.FETCH_PRODUCTS)
    public ResponseEntity<ApiResponse> fetchProducts() {
        return new ResponseEntity<>(new ApiResponse("Product List", productService.fetchProducts()), HttpStatus.OK);
    }

}
