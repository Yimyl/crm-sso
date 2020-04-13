package edu.bjtu.crm.sso.web.controller;

import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserMng {
    @RequestMapping("/userMng")
    public String userMng(Model model) {
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("isMng",userInfo.getIsMng());
        return "userMng";
    }
}
