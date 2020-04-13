package edu.bjtu.crm.sso.web.controller;

import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.web.constant.LoginInfo;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Home {
    @Resource
    private UserMngService userMngService;

    @RequestMapping("/home")
    public String login(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if(LoginInfo.TOKEN.equals(cookies[i].getName())) {
                    model.addAttribute("username", cookies[i].getValue());
                    UserLocal.set(userMngService.findUserInfoByUsername(cookies[i].getValue()));
                    System.out.println("yes");
                    return "home";
                }
            }
        }
        return "/login";
    }

}

