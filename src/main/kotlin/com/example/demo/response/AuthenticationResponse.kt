package com.example.demo.response

class AuthenticationResponse( val responsecode: String, val responsemessage: String,
                              val name: String, val mobile: String, val email: String,
                              val roles: String, val privileges : String,
                              val jwt: String)