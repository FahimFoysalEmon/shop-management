package com.shop.shopmanagementbackend.products;

import com.shop.shopmanagementbackend.categories.utils.CategoryEnums;
import com.shop.shopmanagementbackend.common.ApiResponse;
import com.shop.shopmanagementbackend.products.utils.ProductEndPointUtils;
import jakarta.validation.Valid;
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
                                                   @RequestBody @Valid Product product) {
        return new ResponseEntity<>(new ApiResponse("Product added", productService.saveProduct(categoryId,product)), HttpStatus.CREATED);
    }


    @GetMapping(value = ProductEndPointUtils.FETCH_PRODUCTS)
    public ResponseEntity<ApiResponse> fetchProducts() {
        return new ResponseEntity<>(new ApiResponse("Product List", productService.fetchProducts()), HttpStatus.OK);
    }


    @PutMapping(value = ProductEndPointUtils.UPDATE_PRODUCT)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String productId,
                                                     @RequestBody @Valid Product product) {
        return new ResponseEntity<>(new ApiResponse("Updating Product", productService.updateProduct(productId, product)), HttpStatus.OK);
    }

    @DeleteMapping(value = ProductEndPointUtils.DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String productId) {
        return new ResponseEntity<>(new ApiResponse("Deleting Product", productService.deleteProduct(productId)), HttpStatus.OK);
    }

}
