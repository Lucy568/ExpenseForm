package com.example.demo.controller

import com.example.demo.request.AuthenticationRequest
import com.example.demo.response.BaseResponse
import com.example.demo.service.CustomUserDetailsService
import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val userDetailsService: CustomUserDetailsService, private val authenticationManager: AuthenticationManager,
                     private val userService: UserService) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody authenticationRequest: AuthenticationRequest, errors: Errors): ResponseEntity<*> {
        return if (errors.hasErrors())
            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString ( ", " )))
        else {
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username!!)
        val user = userDetailsService.getUser(authenticationRequest.username!!) ?: throw BadCredentialsException("user does not exist")
        return ResponseEntity.ok(userService.loginUser(authenticationRequest, user, userDetails))
        }
    }

//    @PostMapping("/details")
//    fun getdetails(): UserResponse {
//        val username = SecurityContextHolder.getContext().authentication.name ?: error("You are not authenticated")
//        val user = userDetailsService.getUser(username)!!
//        val userresp = UserResponse("00", "success", user.name!!, user.email!!, user.mobilenumber!!,
//                user.customerid!!, user.roles!!.map { it.name }.joinToString (separator = ","), user.username!! )
//        return userresp
//    }
//
//    @PostMapping("/profiles")
//    fun getprofiles(@RequestBody input: Map<String, String>): ListResponse {
//        val phone = input["phonenumber"] ?: throw IllegalStateException("phone number is required")
//        val profiles = this.userService.getProfiles(phone.substring(1))
//        if (profiles.size > 1)
//            return ListResponse("00", "success", profiles)
//        else{
//            val tayoresp =  this.rubiesService.getProfilesForPhone(phone)
//            when(tayoresp.responsecode){
//                "00" -> { return  ListResponse("00", "success", userService.createProfileRecords(tayoresp.result!!)) }
//                else -> throw IllegalStateException(tayoresp.responsemessage)
//            }
//
//        }
//
//
//    }


//    @PostMapping("/resetpassword")
//    fun resetProfile(@Valid @RequestBody resetRequest: CompleteResetRequest, errors: Errors) : ResponseEntity<*> {
//        val user = userDetailsService.getUser(resetRequest.username!!)
//        val changed = this.rubiesService.resetUserProfile(resetRequest);
//        return when(changed.responsecode){
//            "00" -> {
//                if(user!!.username != " ") userService.changePassword(resetRequest.password, user.id!!)
//                ResponseEntity.ok(changed)
//            }
//            else ->   ResponseEntity.ok(changed)
//        }
//
//    }
//
//    @PostMapping("/forgotpassword")
//    fun forgotPassword(@Valid @RequestBody resetRequest: CompleteResetRequest, errors: Errors) : ResponseEntity<*> {
//        return ResponseEntity.ok(this.rubiesService.forgotPassword(resetRequest))
//    }


    @ExceptionHandler(Throwable::class)
    fun handleGlobalException(ex: Throwable): ResponseEntity<*> {
        val response = BaseResponse("95", ex.message.toString())
        return ResponseEntity.ok(response)
    }

}
