package com.example.demo.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CreateRoleRequest(
    @get:NotEmpty(message = "Role name is Required")
    @get: Size(min = 2, max = 20, message = "Privilege must be between 2 and 20 characters")
    var rolename: String?,
    @get: NotEmpty(message = "Privilege ids are Required")
    var privilegeids: List<Int>? = emptyList()
)