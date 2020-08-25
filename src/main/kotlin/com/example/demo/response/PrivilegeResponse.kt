package com.example.demo.response

data class PrivilegeResponse(
        var privilegename: String,
        var roles: String,
        override var responsecode : String?,
        override var responsemessage : String?): BaseResponse()