package com.example.demo.response

open class BaseResponse {
    open var responsecode: String? =  null
    open var responsemessage: String? = null

    //need default constructor for JSON Parsing
    constructor() {}

    constructor(responsecode: String, responsemessage: String){
        this.responsecode = responsecode
        this.responsemessage = responsemessage
    }
}