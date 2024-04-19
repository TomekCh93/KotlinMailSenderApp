package com.example.demo.email.service

import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class SmtpEmailSender(
    private val mailSender: JavaMailSender,
    @Value("\${self.email.from}") val from: String,
) : EmailSender {
    override fun sendEmail(to: String, subject: String, body: String) {
        val message: MimeMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(body, true)
        helper.setFrom(from)
        mailSender.send(message)
    }
}