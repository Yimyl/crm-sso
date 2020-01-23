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
    public String login() {
        System.out.println(userMapper.findUserByIdAndName("zzj", "hh"));
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/findAccount")
    public String findAccount() {
        return "findAccount";
    }
}
