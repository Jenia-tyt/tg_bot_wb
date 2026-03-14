package com.jeniatyt.secure

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service

@Service
class DisableBasicAuthFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        // Проверяем наличие заголовка Authorization
        val authHeader = httpRequest.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Если это basic auth, отправляем 401 без заголовка WWW-Authenticate
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            return
        }

        chain.doFilter(request, response)
    }
}
