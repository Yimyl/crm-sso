package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.web.constant.ApiEnum;
import edu.bjtu.crm.sso.web.constant.Bar;
import edu.bjtu.crm.sso.web.constant.LoginStatusEnum;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserMng {
    @Resource
    private UserMngService userMngService;

    @RequestMapping("/userMng")
    public String userMng(Model model) {
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("个人信息", "userinfo()"));
        bars.add(new Bar("用户查询", "userinfoSearch()"));
        bars.add(new Bar("添加用户", "userAdd()"));
        model.addAttribute("bars",bars);
        return "userMng";
    }

    @RequestMapping("/userMng/id/{id}")
    public String userMngQueryById(@PathVariable("id") long id, Model model) {
        UserInfo userInfo = userMngService.findUserInfoById(id);
        model.addAttribute("userinfo",userInfo);
        return "userMng";
    }

    @RequestMapping("/userMng/username/{username}")
    public String userMngQueryByUsername(@PathVariable("username") String username, Model model) {
        UserInfo userInfo = userMngService.findUserInfoByUsername(username);
        model.addAttribute("userinfoSearch",userInfo);
        return "userMng";
    }

    @RequestMapping("/userMng/search")
    @ResponseBody
    public CrmResponse<List<UserInfo>> userMngQuery(@RequestBody String param, Model model) {
        CrmResponse<List<UserInfo>> response = new CrmResponse<>();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        UserInfo userInfo = JSONObject.parseObject(param, UserInfo.class);
        List<UserInfo> list = new ArrayList<>();//search
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping(value = "/userMng/userAdd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse<UserInfo> userMngUserAdd(@RequestBody String param, Model model) {
        CrmResponse<UserInfo> response = new CrmResponse<>();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        UserInfo userInfo = JSONObject.parseObject(param, UserInfo.class);
        User user = new User();
        user.setPassword(userInfo.getPassword());
        String username = userMngService.addUser(user,userInfo);
        if ("".equals(username)) {
            response.setCode(ApiEnum.ERROR.getCode());
            response.setMessage(ApiEnum.ERROR.getValue());
            return response;
        }
        userInfo.setUsername(username);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(userInfo);
        return response;
    }
}
