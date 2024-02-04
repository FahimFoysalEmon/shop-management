package com.shop.shopmanagementbackend.userAuth.auth;

import com.shop.shopmanagementbackend.common.exception.DataMismatchException;
import com.shop.shopmanagementbackend.userAuth.config.JwtService;
import com.shop.shopmanagementbackend.userAuth.user.Role;
import com.shop.shopmanagementbackend.userAuth.user.User;
import com.shop.shopmanagementbackend.userAuth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .role(Role.SUPER_ADMIN)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public AuthenticationResponse createManager(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .role(Role.ADMIN)
                .build();

        if (userRepository.findByUserEmail(request.getUserEmail()).isPresent()){
            throw new DataMismatchException("This email is already registered");
        }

        if (userRepository.findByUserPhone(request.getUserPhone()).isPresent()){
            throw new DataMismatchException("This phone number is already registered");
        }

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUserEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
