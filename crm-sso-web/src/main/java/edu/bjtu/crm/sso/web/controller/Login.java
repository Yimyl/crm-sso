package edu.bjtu.crm.sso.web.controller;

import edu.bjtu.crm.sso.dao.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class Login {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println(username + "abc" + password);
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String username, String password) {
        System.out.println(username + "abc" + password);

        System.out.println(userMapper.findUserByIdAndName("zzj", "hh"));
        if (userMapper.findUserByIdAndName("zzj", "hh") == 1) {
            return "home";
        }
        return "redirect:/login";
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
