package com.example.demo.config

import com.example.demo.response.BaseResponse
import com.google.gson.Gson
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(req: HttpServletRequest, res: HttpServletResponse, authException: AuthenticationException) {
        res.contentType = "application/json;charset=UTF-8"
        res.status = 200
        val result  = Gson().toJson(BaseResponse("92", "Authorization not in header or expired"))
        return res.writer.write(result)
    }
}