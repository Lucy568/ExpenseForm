package com.example.demo.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class GetExpenseRequest(
        @get:NotEmpty(message = "id name is Required")
        var id: String? = null
)

