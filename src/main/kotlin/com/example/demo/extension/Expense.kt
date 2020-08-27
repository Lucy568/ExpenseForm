package com.example.demo.extension

import com.example.demo.model.ExpenseForm
import com.example.demo.model.Privilege
import com.example.demo.model.Role
import com.example.demo.request.CreateExpenseRequest
import com.example.demo.request.CreateRoleRequest

    fun CreateExpenseRequest.toExpense() : ExpenseForm {
        return ExpenseForm(this.expense_for!!, this.amount!!, this.description!!)

    }