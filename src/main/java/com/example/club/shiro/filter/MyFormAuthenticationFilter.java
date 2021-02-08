package com.example.club.shiro.filter;

import com.example.club.util.ResultType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        ResultType result = new ResultType(-1, "未授权访问", null);
        String json = mapper.writeValueAsString(result);
        PrintWriter out = httpServletResponse.getWriter();
        out.println(json);
        return false;
    }
}
