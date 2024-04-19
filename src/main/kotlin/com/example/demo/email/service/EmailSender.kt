package com.example.demo.email.service

interface EmailSender {
    fun sendEmail(to: String, subject: String, body: String)
}