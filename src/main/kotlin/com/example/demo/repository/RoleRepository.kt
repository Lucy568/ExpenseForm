package com.example.demo.repository

import com.example.demo.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    fun findRoleByRolename(name: String): Role?
    fun existsRoleByRolename(rolename: String?): Boolean
}