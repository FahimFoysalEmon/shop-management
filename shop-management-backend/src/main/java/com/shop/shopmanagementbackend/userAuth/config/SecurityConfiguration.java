package com.shop.shopmanagementbackend.userAuth.config;

import com.shop.shopmanagementbackend.categories.utils.CategoryEndPointUtils;
import com.shop.shopmanagementbackend.products.utils.ProductEndPointUtils;
import com.shop.shopmanagementbackend.userAuth.auth.JwtAuthenticationEntryPoint;
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


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()

                //AUTH

                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.ADMIN_USER_AUTH_REGISTER).permitAll()
                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.AUTHENTICATE).permitAll()
                .requestMatchers(HttpMethod.POST, UserAuthEndPointUtils.CREATE_MANAGER).hasRole(String.valueOf(Role.SUPER_ADMIN))


                //CATEGORY

                .requestMatchers(HttpMethod.POST, CategoryEndPointUtils.SAVE_CATEGORY).hasAnyRole(String.valueOf(Role.SUPER_ADMIN), String.valueOf(Role.ADMIN))
                .requestMatchers(HttpMethod.GET, CategoryEndPointUtils.FETCH_CATEGORIES).permitAll()


                //PRODUCT

                .requestMatchers(HttpMethod.POST, ProductEndPointUtils.SAVE_PRODUCT).hasAnyRole(String.valueOf(Role.SUPER_ADMIN), String.valueOf(Role.ADMIN))
                .requestMatchers(HttpMethod.GET, ProductEndPointUtils.FETCH_PRODUCTS).permitAll()


                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

}
