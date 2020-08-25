package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
 class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(nullable = false, unique = false)
    var rolename: String? = ""

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    private val createdat: Calendar? = null

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    private val updatedat: Calendar? = null

   @JsonIgnore
   @ManyToMany(mappedBy = "roles")
   var users: List<User>? = null

   @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
   @JoinTable(name = "roles_privileges", joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")])
   var privileges: List<Privilege>? = null

    constructor()
    constructor(rolename: String?, privileges: List<Privilege>){
        this.rolename = rolename
        this.privileges = privileges
    }

}