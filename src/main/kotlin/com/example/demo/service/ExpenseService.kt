package com.example.demo.service

import com.example.demo.model.ExpenseForm
import com.example.demo.model.Privilege
import com.example.demo.repository.ExpenseRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.Id

@Service
class ExpenseService (var expenseRepository : ExpenseRepository){
    fun getAllExpense (): List<ExpenseForm> {
        val allExpense = expenseRepository.findAll()
        return allExpense
    }
    fun getSingleExpense (id: Int): ExpenseForm? {
        val oneExpense = expenseRepository.findById(id)
        return when(oneExpense.isPresent){
            true -> oneExpense.get()
            else -> null
        }
    }
    fun deleteExpense (id: Int) {
         expenseRepository.deleteById(id)
    }

    fun createExpense (expenseForm: ExpenseForm) : ExpenseForm = expenseRepository.save(expenseForm);

}