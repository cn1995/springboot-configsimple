package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HashMap<String, Object> outMap = new HashMap<>();
        outMap.put("code", 200);
        outMap.put("message", "success");
        response.setContentType("application/json;charset=UTF-8");
        response.sendRedirect("/");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(outMap));
        writer.close();

    }
}
