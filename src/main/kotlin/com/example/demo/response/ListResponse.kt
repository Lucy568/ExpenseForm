package com.example.demo.response

data class ListResponse(
        override var responsecode : String?,
        override var responsemessage: String?,
        var data: List<*>
) : BaseResponse()