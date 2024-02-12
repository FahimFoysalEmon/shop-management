package com.shop.shopmanagementbackend.userAuth.auth;

import com.shop.shopmanagementbackend.common.ApiResponse;
import com.shop.shopmanagementbackend.userAuth.auth.utils.UserAuthEndPointUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LogoutService logoutService;

    @PostMapping(UserAuthEndPointUtils.ADMIN_USER_AUTH_REGISTER)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(UserAuthEndPointUtils.CREATE_MANAGER)
    public ResponseEntity<AuthenticationResponse> createManager(
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.createManager(request));
    }


    @PostMapping(UserAuthEndPointUtils.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid AuthenticationRequest request
    ) {
//        System.out.println(request.toString());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


//    @PostMapping(UserAuthEndPointUtils.LOGOUT)
//    public ResponseEntity<ApiResponse> register(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication
//    ) {
//        logoutService.logout(request, response, authentication);
//        return new ResponseEntity<>(new ApiResponse("Logging Out"), HttpStatus.OK);
//    }

}
