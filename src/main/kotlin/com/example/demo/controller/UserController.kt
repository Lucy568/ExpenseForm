package com.example.demo.controller

import com.example.demo.extension.toUser
import com.example.demo.model.Role
import com.example.demo.request.UserRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ListResponse
import com.example.demo.service.RoleService
import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserController( private val userService: UserService,
                     private  val roleService: RoleService){


    @GetMapping("/")
    fun readAllUsers()  = ResponseEntity.ok(ListResponse("00", "success",  userService.readAllUsers()))

    @PostMapping("/create")
    fun createUser(@Valid @RequestBody userRequest: UserRequest, errors: Errors) : ResponseEntity<*> {
        return if (errors.hasErrors())
            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString ( ", " )))
        else {

            val roles = userRequest.roles.map { roleService.findRole(it) }
            val user = userRequest.toUser(roles as List<Role>)

            userService.createUser(user)
            return ResponseEntity.ok(BaseResponse("00", "User Creation Successful"))
        }
    }

//    @PostMapping("/password/change/{id}")
//    fun changePassword(@PathVariable("id") id: Int, @Valid @RequestBody changePasswordRequest: ChangePasswordRequest, errors: Errors) : Any {
//        return if (errors.hasErrors())
//            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString (separator = ", ")))
//        else {
//            if (changePasswordRequest.password.equals(changePasswordRequest.repeatpassword, true)){
//                val user = userService.changePassword(changePasswordRequest.password, id)
//                ResponseEntity.ok(BaseResponse("00", "User Creation Successful"))
//            }else{
//                ResponseEntity.ok(BaseResponse("95", "Unable to change password"))
//            }
//        }
//    }

//    @GetMapping("/{id}")
//    fun readSingleUser(@Valid @RequestBody getUserRequest: GetUserRequest, errors: Errors): ResponseEntity<*> {
//        if (errors.hasErrors())
//            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString (separator = ", ")))
//        val user = userService.readSingleUser(getUserRequest.userid!!.toInt());
//        return if (user.isPresent){
//            ResponseEntity.ok(UserResponse("00", "success", user.get().name!!,
//                    user.get().email!!, user.get().mobilenumber!!, user.get().customerid!!,
//                    user.get().roles!!.map { it.name }.joinToString (separator = ","), user.get().username!! ))
//        }else{
//            ResponseEntity.ok(BaseResponse("96", "Unable to find user"))
//        }
//    }

//    @DeleteMapping("/{id}")
//    fun deleteUser(@PathVariable("id") id: Int): ResponseEntity<*> {
//        return if(userService.deleteUser(id))
//            ResponseEntity.ok(BaseResponse("00", "Successfully Deleted"))
//        else
//            ResponseEntity.ok(BaseResponse("97", "Unable to Delete"))
//    }

//    @PatchMapping("/{id}")
//    fun updateUser(@PathVariable("id") id: Int, @Valid @RequestBody userRequest: UserRequest, errors: Errors) : ResponseEntity<*> {
//        return if (errors.hasErrors())
//            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString (separator = ",")))
//        else if(this.userService.updateUser(id,userRequest)){
//            this.userService.updateUser(id,userRequest)
//            ResponseEntity.ok(BaseResponse("00", "User Succesfully Updated"))
//        }else
//            ResponseEntity.ok(BaseResponse("86", "Unable to update user"))
//    }


    @ExceptionHandler(RuntimeException::class)
    fun handleException(ex : RuntimeException) : ResponseEntity<*> {
        val response = BaseResponse("93", ex.message!!)
        return ResponseEntity.ok(response)
    }


    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthException(ex: AuthenticationException) : ResponseEntity<*> {
        val response = BaseResponse("95", ex.message!!)
        return ResponseEntity.ok(response)
    }


}