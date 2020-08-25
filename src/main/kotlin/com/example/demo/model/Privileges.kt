package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.UniqueElements
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "privileges"
)
class Privilege{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(unique = true)
    var name: String? = null

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    private val createdat: Calendar? = null

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    private val updatedat: Calendar? = null

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "privileges")
    var roles: Collection<Role>? = null

    constructor()

    constructor(name: String){
        this.name = name
    }

}
