package com.example.demo.email.boundary

import com.example.demo.email.exception.EmailSendingException
import com.example.demo.email.service.ApiEmailSender
import com.example.demo.email.service.SmtpEmailSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/sendEmail")
class EmailController  @Autowired internal constructor(
    private val apiEmailSender: ApiEmailSender,
    private val smtpEmailSender: SmtpEmailSender
)  {
    @PostMapping
    fun sendEmail(
        @RequestParam("email") email: String,
        @RequestParam("useSmtp") useSmtp: Boolean
    ): String {
        try {
            if (useSmtp) {
                smtpEmailSender.sendEmail(email, "This is a test email sent via smtp ", "Congrats for sending test email!")
            } else {
                apiEmailSender.sendEmail(email, "This is a test email sent via api", "Congrats for sending test email!")
            }
        } catch (e: Exception) {
            throw EmailSendingException("Sending of e-mail failed", e)
        }

        return "redirect:/confirmation"
    }
}