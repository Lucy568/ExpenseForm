package com.example.demo.service

import com.example.demo.model.Role
import com.example.demo.model.User
import com.example.demo.repository.RoleRepository
import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.transaction.Transactional


@Service
@Transactional
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var roleRepository : RoleRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = this.getUser(username)  ?: throw UsernameNotFoundException("user does not exist")
        return org.springframework.security.core.userdetails.User(
                user.email, user.password, true, true, true,
                true, getAuthoritiesFunctional(user.roles))
    }

    fun getUser(username: String): User? {
        return userRepository.findUserByEmail(username)
    }



    private fun getAuthoritiesFunctional(roles: List<Role>): Collection <GrantedAuthority> {
        return roles.flatMap { it.privileges!! }
                .map { it.name!! }
                .map { SimpleGrantedAuthority(it) }
                .toList()
    }
}