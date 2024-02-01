package com.shop.shopmanagementbackend.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private int code;
    private String msg;
    private List<String> errors;

}
