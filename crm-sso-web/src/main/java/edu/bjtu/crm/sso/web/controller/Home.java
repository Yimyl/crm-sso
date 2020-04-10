package edu.bjtu.crm.sso.web.controller;

import edu.bjtu.crm.sso.web.constant.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class Home {
    @RequestMapping("/home")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
//            System.out.println(cookies[i].getName() +" " +cookies[i].getValue());
                if(LoginInfo.TOKEN.equals(cookies[i].getName())) {
                    model.addAttribute("username", cookies[i].getValue());
                    System.out.println("yes");
                    return "home";
                }
            }
        }
        return "/login";
    }

}

