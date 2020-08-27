package com.example.demo.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class PrivilegeRequest(
    @get:NotEmpty(message = "Privilege name is Required")
    @Size(min = 2, max = 20, message = "Privilege must be between 2 and 20 characters")
    var privilegename: String? = null
)
