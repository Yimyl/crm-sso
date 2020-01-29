package edu.bjtu.crm.sso.web.controller;

import edu.bjtu.crm.sso.dao.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Login {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, String username, String password) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(cookies[i].getName() +" " +cookies[i].getValue());
            if(cookies[i].getName() == "token") {
                model.addAttribute("username", username);
                return "home";
            }
        }
        System.out.println(username + "abc" + password);
        if (username != null) {
            model.addAttribute("username", username);
        } else {
            model.addAttribute("firstLogin", true);
        }
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpServletResponse response, Model model, String username, String password) {
        System.out.println(username + "abc" + password);

        System.out.println(userMapper.findUserByIdAndName("zzj", "hh"));
        if (userMapper.findUserByIdAndName("zzj", "hh") == 1) {
            Cookie cookie = new Cookie("token", username);
            response.addCookie(cookie);
            return "home";
        }
        request.getSession().setAttribute("validCode", "1234");
        return "login";
    }

    @RequestMapping("/register")
    public String register(String username, String password, String password2, String email) {
        System.out.println(username + " " + password + " " + email);
//        userMapper.addUser(username, password, email);
        return "register";
    }

    @RequestMapping("/doRegister")
    public String doRegister(String username, String password, String password2, String email) {
        System.out.println(username + " " + password + " " + email);
//        userMapper.addUser(username, password, email);
        return "redirect:/register";
    }

    @RequestMapping("/findAccount")
    public String findAccount() {
        return "findAccount";
    }

    @RequestMapping("/doFindAccount")
    public String doFindAccount() {
        return "redirect:/findAccount";
    }
}
