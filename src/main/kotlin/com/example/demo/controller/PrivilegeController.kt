package com.example.demo.controller

import com.example.demo.request.PrivilegeRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ListResponse
import com.example.demo.response.PrivilegeResponse
import com.example.demo.service.PrivilegeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/privileges")
class PrivilegeController {

    @Autowired
    lateinit var privilegeService: PrivilegeService

    @GetMapping("/")
    fun readAllPrivileges() = ListResponse("00", "Success", privilegeService.readAllPrivileges())

    @PostMapping("/create")
    fun createNewPrivilege(@Valid @RequestBody privilegeRequest: PrivilegeRequest, errors: Errors) : ResponseEntity<*> {
        return if (errors.hasErrors()){
            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString(separator = ", ")))
        }else {
            if (privilegeService.checkPrivilegeExists(privilegeRequest.privilegename!!.toLowerCase())){
                return ResponseEntity.ok(BaseResponse("00", "privilege name already exist"))
            }else{
                val privilege = privilegeService.createPrivilege(privilegeRequest.privilegename!!)
                ResponseEntity.ok( BaseResponse("00", "Successful"))
            }

        }
    }

//    @GetMapping("/{id}")
//    fun readSinglePrivilege(@PathVariable("id") id: Int): PrivilegeResponse {
//        val privilege = privilegeService.readSinglePrivilege(id)
//        if (privilege.isPresent){
//            return PrivilegeResponse(privilege.get().name!!, privilege.get().roles?.map {  it.rolename }!!.joinToString (separator = ","), "00", "Successful")
//        }
//        return PrivilegeResponse("", "", "95", "Record Not Found")
//    }

//    @DeleteMapping("/{id}")
//    fun deletePrivilege(@PathVariable("id") id: Int): BaseResponse {
//        return if(privilegeService.deletePrivilege(id))
//            BaseResponse("00", "Successfully Deleted")
//        else
//            BaseResponse("97", "Unable to Delete")
//    }


//    @PatchMapping("/{id}")
//    fun updatePrivilege(@PathVariable("id") id: Int, @Valid @RequestBody privilegeRequest: PrivilegeRequest, errors: Errors) : BaseResponse {
//        return if (errors.hasErrors()) {
//            BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString(separator = ","))
//        } else if( privilegeService.updatePrivilege(id, privilegeRequest))
//            BaseResponse( "00", "Successful")
//        else
//            BaseResponse( "00", "Unable to modify privilege")
//
//    }
//
//    @ExceptionHandler(AuthenticationException::class)
//    fun handleAuthException(ex: AuthenticationException) : ResponseEntity<*> {
//        val response = BaseResponse("95", ex.message!!)
//        return ResponseEntity.ok(response)
//    }

}