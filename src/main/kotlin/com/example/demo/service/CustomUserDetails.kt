package com.example.demo.service

import com.example.demo.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class CustomUserDetails : UserDetails {
    private var authorities: Set<GrantedAuthority>? = null
    private var user: User? = null

    override fun getAuthorities(): MutableList<out GrantedAuthority> {
        return authorities!!.toMutableList();
    }

    override fun isEnabled(): Boolean {
        return true;
    }

    override fun getUsername(): String {
        return user!!.email.toString()
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun getPassword(): String {
        return user?.password.toString();
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }
}
