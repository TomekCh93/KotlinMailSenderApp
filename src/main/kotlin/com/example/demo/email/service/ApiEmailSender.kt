package com.example.demo.email.service

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ApiEmailSender(
    @Value("\${self.email.apiToken}") val apiToken: String,
    @Value("\${self.email.apiUrl}") val apiUrl: String,
    @Value("\${self.email.from}") val from: String,
) : EmailSender {
    override fun sendEmail(to: String, subject: String, body: String) {
        val client = OkHttpClient()
        val jsonMediaType = "application/json".toMediaTypeOrNull()
        val requestBody = """
    {
        "from": {
            "email": "$from",
            "name": "Mailtrap Test"
        },
        "to": [
            {
                "email": "$to"
            }
        ],
        "subject": "$subject",
        "text": "$body",
        "category": "Integration Test"
    }
""".trimIndent().toRequestBody(jsonMediaType)

        val request = Request.Builder()
            .url(apiUrl)
            .method("POST", requestBody)
            .addHeader("Authorization", "Bearer $apiToken")
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                println("Error: ${response.code} ${response.message}")
            } else {
                println("Message sent successfully!")
            }
        }
    }
}