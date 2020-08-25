package com.example.demo.model
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
     var id:Int? = null

    @Column(nullable = false)
    var name: @NotEmpty String? = null
    @Column(nullable = false, unique = true)
    var email: @NotEmpty String? = null
    @Column(nullable = false, unique = false)
    var password: String? = null
    @Column(nullable = false, unique = true)
    var mobilenumber: @NotEmpty String? = null


    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
    var roles: List<Role> = emptyList()

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    val createdat: Calendar? = null

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    val updatedat: Calendar? = null

    constructor()

    constructor(name: String?, email: String?, mobilenumber: String?, password: String?, roles:List<Role>) {
        this.name = name
        this.email = email
        this.mobilenumber = mobilenumber
        this.password = password
        this.roles = roles
    }
}