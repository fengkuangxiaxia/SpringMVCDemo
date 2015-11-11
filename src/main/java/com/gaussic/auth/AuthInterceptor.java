package com.gaussic.auth;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Cole Fu on 2015/11/11.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

            //没有声明需要权限,或者声明不验证权限
            //if(authPassport == null || authPassport.validate() == false)
            //    return true;

            //在这里实现自己的权限验证逻辑
            Object obj = request.getSession().getAttribute("loginUser");
            if(obj == null){
                //request.getRequestDispatcher("/login.jsp").forward(request, response);
                response.sendRedirect("/account/login");
                return false;
            }
            else {
                return true;
            }
        }
        else
            return true;
    }
}
