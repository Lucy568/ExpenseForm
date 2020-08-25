package com.example.demo.extension

import com.example.demo.model.Privilege
import com.example.demo.model.Role
import com.example.demo.request.CreateRoleRequest
import com.example.demo.service.PrivilegeService

fun CreateRoleRequest.toRoles(privileges:List<Privilege>) : Role {
    return Role(this.rolename, privileges)
}