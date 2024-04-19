package com.example.demo.email.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class EmailSendingException(message: String, cause: Throwable?) : RuntimeException(message, cause)