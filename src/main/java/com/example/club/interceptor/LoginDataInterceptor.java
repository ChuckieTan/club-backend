package com.example.club.interceptor;

import com.example.club.model.LoginData;
import com.example.club.service.LoginDataService;
import com.example.club.service.UserService;
import com.example.club.util.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginDataInterceptor implements HandlerInterceptor {
    @Resource
    LoginDataService loginDataService;

    @Resource
    UserService userService;

    @Resource
    IpUtil ipUtil;

    Logger logger = LoggerFactory.getLogger(LoginDataInterceptor.class);

    protected String getUrl(HttpServletRequest request) {
        String url = "";
        url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getServletPath();
        if (request.getQueryString() != null) {
            url += "?" + request.getQueryString();
        }
        return url;
    }

    protected String getDeviceName(HttpServletRequest request) {
        Device device = DeviceUtils.getCurrentDevice(request);
        if(device.isMobile()) {
            return "Mobile";
        } else if(device.isTablet()) {
                return "Tablet";
        } else if(device.isNormal()) {
            return "PC";
        } else {
            return "Unknown";
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView)
            throws Exception {
        String url = getUrl(request);

        String principal = (String) SecurityUtils.getSubject().getPrincipal();

        if (principal != null) {
            Integer userId = userService.queryInfoByNumber(principal).getUserId();
            String ip = IpUtil.getIpAddr(request);
            String device = getDeviceName(request);
            LoginData loginData = new LoginData(userId, new Date(), ip, device);
            if ("/api/login".equals(request.getServletPath())) {
                loginDataService.insertNewRecord(loginData);
            }
            logger.info(principal + "访问页面" + url);
            logger.info("登录信息: " + loginData);
        } else {
            logger.info("匿名用户访问页面" + url);
        }

    }
}
