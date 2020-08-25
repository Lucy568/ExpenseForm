package com.example.demo.service

import com.example.demo.model.Privilege
import com.example.demo.model.Role
import com.example.demo.repository.PrivilegeRepository
import com.example.demo.request.PrivilegeRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class PrivilegeService(var privilegeRepository : PrivilegeRepository) {

    fun createPrivilege (name: String) : Privilege = privilegeRepository.save(Privilege(name));

    fun readSinglePrivilege(id : Int): Privilege? {
        val privilege = privilegeRepository.findById(id)
        return when(privilege.isPresent){
            true -> privilege.get()
            else -> null
        }
    }

    fun readAllPrivileges(): List<Privilege> {
        return privilegeRepository.findAll()
    }

    fun updatePrivilege(privilege: Privilege){
        privilegeRepository.save(privilege)
    }

    fun deletePrivilege(id: Int) {
        privilegeRepository.deleteById(id)
    }

    fun checkPrivilegeExists(name: String) : Boolean{
        return privilegeRepository.existsByName(name)
    }
}