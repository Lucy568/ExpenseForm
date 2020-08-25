package com.example.demo.extension

import com.example.demo.model.Role
import com.example.demo.model.User
import com.example.demo.request.UserRequest

fun UserRequest.toUser(roles:List<Role>) : User{
    return User(this.name, this.email, this.mobilenumber, this.password, roles)
}