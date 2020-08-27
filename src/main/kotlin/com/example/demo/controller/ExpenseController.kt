package com.example.demo.controller

import com.example.demo.extension.toExpense
import com.example.demo.extension.toUser
import com.example.demo.model.ExpenseForm
import com.example.demo.model.Role
import com.example.demo.request.CreateExpenseRequest
import com.example.demo.request.GetExpenseRequest
import com.example.demo.request.UserRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ListResponse
import com.example.demo.service.ExpenseService
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Id
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/expense")
class ExpenseController (private val expenseService: ExpenseService) {

    @PostMapping("/")
    fun getAllExpense(): ListResponse {
        val  all_expense = expenseService.getAllExpense()
        return  ListResponse("00", "Success",all_expense)
    }

    @PostMapping("/single")
    fun getSingleExpense(getExpenseRequest: GetExpenseRequest): ExpenseForm? {
        val single_expense = expenseService.getSingleExpense(getExpenseRequest.id!!.toInt())
        return single_expense
    }

    @PostMapping("/create")
    fun createExpense(@Valid @RequestBody createExpenseRequest: CreateExpenseRequest, errors: Errors) : ResponseEntity<*> {
        return if (errors.hasErrors())
            ResponseEntity.ok(BaseResponse("96", errors.allErrors.map { e -> e.defaultMessage }.joinToString ( ", " )))
        else {
           expenseService.createExpense(createExpenseRequest.toExpense())
            return ResponseEntity.ok(BaseResponse("00", "User Creation Successful"))
        }
    }


}