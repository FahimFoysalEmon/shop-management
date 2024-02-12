package com.shop.shopmanagementbackend.userAuth.config;

import com.shop.shopmanagementbackend.categories.utils.CategoryEndPointUtils;
import com.shop.shopmanagementbackend.products.utils.ProductEndPointUtils;
import com.shop.shopmanagementbackend.userAuth.auth.JwtAuthenticationEntryPoint;
import com.shop.shopmanagementbackend.userAuth.auth.LogoutService;
import com.shop.shopmanagementbackend.userAuth.auth.utils.UserAuthEndPointUtils;
import com.shop.shopmanagementbackend.userAuth.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final LogoutService logoutService;


    private final String USER = "USER";
    private final String ADMIN = "ADMIN";
    private final String SUPER_ADMIN = "SUPER_ADMIN";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()

                //AUTH

                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.ADMIN_USER_AUTH_REGISTER).permitAll()
                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.AUTHENTICATE).permitAll()
                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.CREATE_MANAGER).hasRole(SUPER_ADMIN)
                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.LOGOUT).hasAnyRole(ADMIN, SUPER_ADMIN)


                //CATEGORY

                .requestMatchers(HttpMethod.POST, CategoryEndPointUtils.SAVE_CATEGORY).hasAnyRole(SUPER_ADMIN, ADMIN)
                .requestMatchers(HttpMethod.GET, CategoryEndPointUtils.FETCH_CATEGORIES).hasAnyRole(SUPER_ADMIN, ADMIN)


                //PRODUCT

                .requestMatchers(HttpMethod.POST, ProductEndPointUtils.SAVE_PRODUCT).hasAnyRole(ADMIN, SUPER_ADMIN)
                .requestMatchers(HttpMethod.GET, ProductEndPointUtils.FETCH_PRODUCTS).permitAll()
                .requestMatchers(HttpMethod.PUT, ProductEndPointUtils.UPDATE_PRODUCT).hasAnyRole(ADMIN, SUPER_ADMIN)
                .requestMatchers(HttpMethod.DELETE, ProductEndPointUtils.DELETE_PRODUCT).hasAnyRole(ADMIN, SUPER_ADMIN)


                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutUrl(UserAuthEndPointUtils.LOGOUT) // Specify the logout URL
                .logoutSuccessHandler(logoutService::logout) // Set the custom LogoutService
                .permitAll() // Allow all users to access the logout endpoint
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

}
