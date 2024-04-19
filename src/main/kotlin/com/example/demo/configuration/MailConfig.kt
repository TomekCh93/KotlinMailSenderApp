package com.example.demo.confirmation

import jakarta.mail.Authenticator
import jakarta.mail.PasswordAuthentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig @Autowired constructor(
    @Value("\${self.email.host}") val host: String,
    @Value("\${self.email.port}") val port: Int,
    @Value("\${self.email.username}") val username: String,
    @Value("\${self.email.password}") val password: String,
) {
    @Bean
    fun getJavaMailSender(): JavaMailSenderImpl {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port

        mailSender.username = username
        mailSender.password = password

        val props = mailSender.javaMailProperties
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.starttls.required"] = "true"
        props["mail.smtp.auth"] = true
        props["mail.smtp.connectiontimeout"] = "5000"
        props["mail.smtp.timeout"] = "5000"
        props["mail.smtp.writetimeout"] = "5000"

        val session = jakarta.mail.Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        mailSender.session = session

        return mailSender
    }
}
