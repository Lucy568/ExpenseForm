package com.example.demo.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CreateExpenseRequest (
         @get:NotEmpty(message = "Expense name is required")
         @Size(min = 2, max = 20 , message = "Expense must be between 2 and 20 characters")
         var expense_for : String? = null,

        @get:NotEmpty(message =  "amount is required")
       @Size(min = 2, max = 20 , message = "amount must be between 2 and 20 characters")
       var amount : String? = null,


       @get:NotEmpty(message = "description is required")
       @Size(min = 2, max = 20 , message = "Expense must be between 2 and 20 characters")
       var description : String? = null

)
