package com.example.demo.service

import com.example.demo.model.Role
import com.example.demo.repository.RoleRepository
import com.example.demo.request.CreateRoleRequest
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository,
                  private val privilegeService: PrivilegeService){

    fun createRole(role: Role):Boolean{
        val savedRole = roleRepository.save(role)
        return savedRole.id != null
    }

    fun getAllRoles(): List<Role>{
       val roles = roleRepository.findAll()
       return roles
    }

    fun findRole(id : Int): Role? {
      val role =  roleRepository.findById(id)
       when(role.isPresent){
           true -> return role.get()
           else -> return null;
       }
    }

}