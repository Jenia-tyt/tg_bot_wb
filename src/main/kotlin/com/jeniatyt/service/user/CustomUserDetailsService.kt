package com.example.uploadapp.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    private val expect: String = "ANTON_YAKOVLEV"
    private val pass: String = "RUSSIA_2026_@!"

    override fun loadUserByUsername(username: String): UserDetails {
        if (expect == username) {
            return User(expect, pass, listOf())
        }

        throw UsernameNotFoundException("Тебе сюда нельзя: $username")
    }
}
