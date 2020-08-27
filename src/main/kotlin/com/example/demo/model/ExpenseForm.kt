package com.example.demo.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import sun.security.x509.AccessDescription
import java.time.temporal.TemporalAmount
import java.util.*
import javax.persistence.*

@Entity
@Table
class ExpenseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private var id : Int? = null

    private var expense_for : String? = ""
    private var amount : String? = ""

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
     private var created_date : Calendar? = null
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    private var updated_at : Calendar? = null
    private var description : String? = ""

    constructor()

    constructor(expense_for : String , amount: String , description: String){
        this.expense_for = expense_for
        this.amount = amount
        this.description = description
    }
}