package com.example.demo.service

import com.example.demo.model.Role
import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import com.example.demo.request.AuthenticationRequest
import com.example.demo.response.AuthenticationResponse
import com.example.demo.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@Service
class UserService {

    @Autowired
    lateinit var userRepository : UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenUtil: JwtUtil

   fun getAllUsers():List<User>{
       return userRepository.findAll()
   }

    fun getSingleUser(id:Int): Optional<User> {
        return userRepository.findById(id)
    }

    fun createUser(user: User) {
        user.password = this.passwordEncoder.encode(user.password)
        userRepository.save(user)
    }

    fun readAllUsers(): List<User>{
        return userRepository.findAll();
    }

    fun loginUser(authenticationRequest: AuthenticationRequest, user: User, userDetails: UserDetails) : AuthenticationResponse {
        val auth = UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password)
        authenticationManager.authenticate(auth)
        val jwt = jwtTokenUtil.generateToken(userDetails)
        return AuthenticationResponse(
                responsecode = "00",
                responsemessage = "Login Successful",
                jwt = jwt,
                name = user.name!!,
                mobile = user.mobilenumber!!,
                email = user.email!!,
                roles = user.roles.map { it.rolename }.joinToString(separator = ", "),
                privileges = user.roles.flatMap { it.privileges!! }?.map { it.name }?.joinToString(separator = ",")!!
        )

    }

}
