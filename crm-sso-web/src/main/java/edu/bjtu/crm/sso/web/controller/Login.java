package edu.bjtu.crm.sso.web.controller;

import edu.bjtu.crm.sso.dao.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
public class Login {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/")
    @ResponseBody
    public String login() {
        System.out.println(userMapper.findUserByIdAndName("zzj", "hh"));
        return "asd";
    }


}
