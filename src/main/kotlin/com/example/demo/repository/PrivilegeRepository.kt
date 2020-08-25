package com.example.demo.repository

import com.example.demo.model.Privilege
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PrivilegeRepository:JpaRepository<Privilege, Int> {
    fun existsByName(name: String) : Boolean
}