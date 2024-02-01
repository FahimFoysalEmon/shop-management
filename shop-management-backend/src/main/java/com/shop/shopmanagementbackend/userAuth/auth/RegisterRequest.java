package com.shop.shopmanagementbackend.userAuth.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPassword;

}
