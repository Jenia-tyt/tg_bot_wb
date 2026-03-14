package com.jeniatyt.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf{
                it.disable()
            }
            .cors {
                it.disable()
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/",
                        "/login",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**",
                        "/favicon.ico",
                        "/error",
                        "/public/**",
                        "/api/public/**",
                        "/actuator/*"
                    ).permitAll()

                    .requestMatchers("/upload/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/upload/*").hasRole("ADMIN")

                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .failureForwardUrl("/upload.html")
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
            }

        return http.build()
    }

    @Bean
    fun reactiveUserDetailsService(): InMemoryUserDetailsManager {
        return InMemoryUserDetailsManager(
            User.withUsername("ANTON_YAKOVLEBV")
                .password("{noop}Russia_2026!")
                .roles("ADMIN")
                .build()
        )
    }
}
