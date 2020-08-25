package com.example.demo.controller


import com.example.demo.extension.toRoles
import com.example.demo.model.Privilege
import com.example.demo.request.CreateRoleRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ListResponse
import com.example.demo.service.PrivilegeService
import com.example.demo.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/roles")
class RoleController(private val privilegeService: PrivilegeService, private val roleService: RoleService) {

    @GetMapping("/")
    fun getAllRoles(): ResponseEntity<*> {
        val roles = this.roleService.getAllRoles();
        return ResponseEntity.ok(ListResponse("00", "Successs", roles))
    }

    @PostMapping("/create")
    fun createNewRole(@Valid @RequestBody createRoleRequest: CreateRoleRequest, errors: Errors): ResponseEntity<*> {
        if (errors.hasErrors())
            return ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString(separator = ",")))
        else {
            val listOfPrivileges = createRoleRequest.privilegeids?.map { privilegeService.readSinglePrivilege(it) }
            val role = createRoleRequest.toRoles(listOfPrivileges as List<Privilege>)
            this.roleService.createRole(role)
            return ResponseEntity.ok(BaseResponse("00", "Role Created"))
        }
    }

    @ExceptionHandler(Throwable::class)
    fun handleGlobalException(ex: Throwable): ResponseEntity<*> {
        val response = BaseResponse("95", ex.message.toString())
        return ResponseEntity.ok(response)
    }


}