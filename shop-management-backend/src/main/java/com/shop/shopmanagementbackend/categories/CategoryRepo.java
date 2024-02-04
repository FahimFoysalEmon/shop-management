package com.shop.shopmanagementbackend.categories;

import com.shop.shopmanagementbackend.categories.utils.CategoryEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {

    boolean existsByName(CategoryEnums name);

}
