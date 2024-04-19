package com.example.demo.confirmation.boundary

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/confirmation")
class ConfirmationController {
    @GetMapping
    fun showConfirmationPage(): String {
        return "confirmation"
    }
}
