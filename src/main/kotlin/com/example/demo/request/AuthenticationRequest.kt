package com.example.demo.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class AuthenticationRequest(
    @get:NotEmpty(message = "Username must not be empty")
    @get:Size(min = 3, max = 40, message = "username must be between 3 and 40 characters")
    var username: String?,
    @get:NotEmpty(message = "password must not be empty")
    @get:Size(min = 6, max = 13, message = "password must be between 6 and 13 characters")
    var password: String?,
    @get:NotEmpty(message = "device id  is required")
    @get:Size(min = 1, max = 50, message = "device id must be between 1 and 50 characters")
    var deviceid: String?
)