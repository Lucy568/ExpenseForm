package com.example.demo.response

class UserResponse (
    override var responsecode:String?,
    override var responsemessage : String?,
    var name: String,
    var email: String,
    var mobile: String,
    var customerid: String,
    var roles: String
):BaseResponse()