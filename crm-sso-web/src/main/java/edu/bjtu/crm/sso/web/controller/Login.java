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
import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(cookies[i].getName() +" " +cookies[i].getValue());
            if(cookies[i].getName() == "token") {
                model.addAttribute("username", cookies[i].getValue());
                return "home";
            }
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("validcode") == null){
            model.addAttribute("firstLogin", true);
        } else {
            session.setAttribute("validcode", "1234");
        }
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String validcode, String remember) {
        System.out.println(username + "abc" + password);
        HttpSession session = request.getSession();
        System.out.println(userMapper.findUserByIdAndName("zzj", "hh"));
        //校验验证码
        if (session.getAttribute("validcode") != null && session.getAttribute("validcode").equals(validcode)) {
            //生成验证码
            session.setAttribute("validcode", "1234");
            return "validcodeError";
        }
        //验证账号
        if (userMapper.findUserByIdAndName("zzj", "hh") == 1) {
            session.removeAttribute("validcode");
            //给token
            Cookie cookie = new Cookie("token", username);
            if (remember == "remember") {
                cookie.setMaxAge(7*24*3600);
            } else {
                cookie.setMaxAge(-1);
            }
            response.addCookie(cookie);
            return "success";
        }
        //生成验证码
        session.setAttribute("validcode", "1234");
        return "usernamepasswordError";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, String username, String password, String password2, String email) {
        request.getSession().setAttribute("registerValidcode", "1234");
        System.out.println(username + " " + password + " " + email);
//        userMapper.addUser(username, password, email);

        return "register";
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(HttpServletRequest request, String username, String password, String password2, String email, String validcode) {

        System.out.println(username + " " + password + " " + email);
        HttpSession session = request.getSession();
        if (username.length() < 6 || username.length() > 20 || password.length() < 6 || password.length() > 16 || !password.equals(password2) || email.length() > 30) {
            session.setAttribute("validcode", "1234");
            return "paramError";
        }
        if (session.getAttribute("validcode") != null && session.getAttribute("registerValidcode").equals(validcode)) {
            //生成验证码
            session.setAttribute("validcode", "1234");
            return "validcodeError";
        }
//        userMapper.addUser(username, password, email);
        return "success";
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
