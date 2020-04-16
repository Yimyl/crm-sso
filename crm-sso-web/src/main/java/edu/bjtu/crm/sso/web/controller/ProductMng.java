package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.web.constant.ApiEnum;
import edu.bjtu.crm.sso.web.constant.Bar;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductMng {
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
        model.addAttribute("userinfoSearch",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("个人信息", "userinfo()"));
        bars.add(new Bar("用户查询", "userinfoSearch()"));
        bars.add(new Bar("添加用户", "userAdd()"));
        model.addAttribute("bars",bars);
        return "userMng";
    }

    @RequestMapping("/userMng/username/{username}")
    public String userMngQueryByUsername(@PathVariable("username") String username, Model model) {
        UserInfo userInfo = userMngService.findUserInfoByUsername(username);
        model.addAttribute("userinfoSearch",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("个人信息", "userinfo()"));
        bars.add(new Bar("用户查询", "userinfoSearch()"));
        bars.add(new Bar("添加用户", "userAdd()"));
        model.addAttribute("bars",bars);
        return "userMng";
    }

    @RequestMapping("/userMng/search")
    @ResponseBody
    public CrmResponse<UserInfo> userMngQuery(@RequestBody String param, Model model) {
        CrmResponse<UserInfo> response = new CrmResponse<>();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        UserInfo userInfo = JSONObject.parseObject(param, UserInfo.class);
        UserInfo res = userMngService.findUserInfoByUserInfo(userInfo);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(res);
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

    @RequestMapping(value = "/userMng/passwordModify", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse userMngPasswordModify(@RequestBody String param, Model model) {
        CrmResponse response = new CrmResponse<>();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        JSONObject userInfo = JSONObject.parseObject(param);
        String username = userInfo.getString("username");
        String password = userInfo.getString("password");
        String passwordNew = userInfo.getString("passwordNew");
        User user = new User(0, username,password);

        if (userMngService.login(user) == 0){
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage("密码错误");
            return response;
        }
        if (userMngService.updatePassword(user, passwordNew) == 1){
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping(value = "/userMng/userInfoModify", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse userMngUserInfoModify(@RequestBody String param, Model model) {
        CrmResponse response = new CrmResponse<>();
        UserInfo userInfo = JSONObject.parseObject(param, UserInfo.class);
        if (UserLocal.getIsMng() == 0 && !UserLocal.getUsername().equals(userInfo.getUsername()) ) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        if (userMngService.updateUserInfo(userInfo) == 1){
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            if (UserLocal.getUsername().equals(userInfo.getUsername())) {
                UserLocal.set(userMngService.findUserInfoByUsername(userInfo.getUsername()));
            }
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping(value = "/userMng/userInfoDelete/{username}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse userMngUserInfoDelete(@PathVariable("username") String username) {
        CrmResponse response = new CrmResponse<>();
        try {
            if (UserLocal.getIsMng() == 0) {
                response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
                response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
                return response;
            }
            System.out.println("delete");
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            System.out.println(username);
            userMngService.deleteUserInfoByUsername(username);
            return response;
        } catch (Exception e) {
            response.setCode(ApiEnum.ERROR.getCode());
            response.setMessage(ApiEnum.ERROR.getValue());
            return response;
        }
    }
}
