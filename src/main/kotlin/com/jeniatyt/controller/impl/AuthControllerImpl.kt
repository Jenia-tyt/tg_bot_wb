package com.jeniatyt.controller.impl

import com.jeniatyt.controller.AuthController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthControllerImpl: AuthController {

    @GetMapping("/login")
    override fun loginPage(): String {
        return "login"
    }

    @GetMapping("/upload")
    override fun uploadPage(): String {
        return "upload.html"
    }

    @GetMapping("/")
    override fun home(): String {
        return "redirect:/upload.html"
    }
}
