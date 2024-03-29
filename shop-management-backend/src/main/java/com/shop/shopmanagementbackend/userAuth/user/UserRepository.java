package com.shop.shopmanagementbackend.userAuth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserPhone(String userPhone);

}
