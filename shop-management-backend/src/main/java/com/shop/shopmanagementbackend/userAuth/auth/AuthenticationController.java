package com.shop.shopmanagementbackend.userAuth.auth;

import com.shop.shopmanagementbackend.userAuth.auth.utils.UserAuthEndPointUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(UserAuthEndPointUtils.ADMIN_USER_AUTH_REGISTER)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }



    @PostMapping(UserAuthEndPointUtils.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid AuthenticationRequest request
    ) {
//        System.out.println(request.toString());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
