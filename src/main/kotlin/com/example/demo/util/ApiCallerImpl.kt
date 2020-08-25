package com.example.demo.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.util.*


@Component
class ApiCallerImpl : ApiCaller {
    override fun postCall(uri: String?, request: Any?, response: Any?): Any? {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()

        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.set("x-api-key", "FINTECH-092a9b5683f54c5280fa73143952ac7e")
        val entity: HttpEntity<Any> = HttpEntity<Any>(request, headers)
        return restTemplate.postForObject(uri!!, entity, response!!.javaClass)
    }

    override fun postStringCall(uri: String, request: HashMap<String, Any>, token: String): HashMap<String, Any?> {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()

        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = token
        val entity = HttpEntity<Any>(request, headers)
        return try {
            val resp = restTemplate.postForObject(uri, entity, String::class.java)!!
            stringToHashMap(resp)
        } catch (err: HttpClientErrorException) {
            val error = HashMap<String, Any?>()
            error["error"] = err.message
            error
        }
    }

    override fun postStringCall(uri: String, request: String, token: String): String {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()

        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = token
        val entity = HttpEntity<Any>(request, headers)
        return try {
            val resp = restTemplate.postForObject(uri, entity, String::class.java)!!
            resp
        } catch (err: HttpClientErrorException) {
            err.toString()
        }
    }

    fun stringToHashMap(Response: String?): HashMap<String, Any?> {
        return try {
            //System.out.println(Arrays.asList(result)); // method 1
            ObjectMapper()
                    .readValue(Response, object : TypeReference<HashMap<String, Any?>?>() {})!!
        } catch (e: IOException) {
            val error = HashMap<String, Any?>()
            error["error"] = e.message
            error
        }
    }


    override fun postStringCall(uri: String?, request: HashMap<String?, Any?>?, token: HashMap<String?, Any?>?): HashMap<String, Any?> {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers[token!!["header"].toString()] = token["token"].toString()
        val entity = HttpEntity<Any>(request, headers)
        return try {
            val resp = restTemplate.postForObject(uri!!, entity, String::class.java)!!
            stringToHashMap(resp)
        } catch (err: HttpClientErrorException) {
            val error = HashMap<String, Any?>()
            error["error"] = err.message
            error
        }
    }

    override fun postStringCall(uri: String?, request: String?, token: HashMap<String?, Any?>?): String? {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()

        headers.contentType = MediaType.APPLICATION_JSON
        headers[token!!["header"].toString()] = token["token"].toString()
        val entity = HttpEntity<Any>(request, headers)
        return try {
            restTemplate.postForObject(uri!!, entity, String::class.java)!!
        } catch (err: HttpClientErrorException) {
            err.message
        }
    }

    override fun postStringCall(uri: String?, request: String?): String? {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(request, headers)
        return try {
            restTemplate.postForObject(uri!!, entity, String::class.java)!!
        } catch (err: HttpClientErrorException) {
            err.localizedMessage
        }
    }

}