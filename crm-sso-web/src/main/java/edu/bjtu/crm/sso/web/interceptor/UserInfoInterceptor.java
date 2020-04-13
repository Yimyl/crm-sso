package edu.bjtu.crm.sso.web.interceptor;

import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.web.constant.LoginInfo;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInfoInterceptor implements HandlerInterceptor {
    @Resource
    private UserMngService userMngService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (UserLocal.get() == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if(LoginInfo.TOKEN.equals(cookies[i].getName())) {
                        UserLocal.set(userMngService.findUserInfoByUsername(cookies[i].getValue()));
                        System.out.println("interceptor add userInfo");
                        return true;
                    }
                }
            }
            return false;
        }


        /**
         * boolean 值： 确定了拦截器其余两方法是否执行
         */
        return true;
    }
}
