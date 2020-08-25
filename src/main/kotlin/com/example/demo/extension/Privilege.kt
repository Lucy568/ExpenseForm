package com.example.demo.extension

import com.example.demo.model.Privilege
import com.example.demo.model.User
import com.example.demo.request.PrivilegeRequest
import com.example.demo.request.UserRequest

fun PrivilegeRequest.toPrivilege() : Privilege {
    return Privilege(this.privilegename.toString())
}