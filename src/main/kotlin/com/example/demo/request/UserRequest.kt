package com.example.demo.request

import com.example.demo.model.Role
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size
import kotlin.collections.HashSet

data class UserRequest(
    @get:NotEmpty(message = "name can not be empty")
    var name:String?,
    @get: NotEmpty(message = "email can not be empty")
    var email: String?,
    @get:NotEmpty(message = "password must not be empty")
    @get: Size(min=6, max= 6,message = "Password must be six characters")
    var password: String?,
    @get: NotEmpty(message = "mobilenumber can not be empty")
    var mobilenumber: String?,
    @get: NotEmpty(message = "role must have at least one item")
    var roles : List<Int> = emptyList()

)