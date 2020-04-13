package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSON;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.web.constant.LoginInfo;
import edu.bjtu.crm.sso.web.constant.LoginStatusEnum;
import edu.bjtu.crm.sso.web.constant.RegisterStatusEnum;
import edu.bjtu.crm.sso.web.util.UserLocal;
import edu.bjtu.crm.sso.web.util.ValidCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class Login {

    @Resource
    private UserMngService userMngService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
//            System.out.println(cookies[i].getName() +" " +cookies[i].getValue());
                if(cookies[i].getName() == LoginInfo.TOKEN) {
                    model.addAttribute("username", cookies[i].getValue());
                    return "home";
                }
            }
        }

        HttpSession session = request.getSession();
        if (session.getAttribute(LoginInfo.VALIDCODE) == null){
            model.addAttribute(LoginInfo.FIRST_LOGIN, true);
        } else {
            createValidcode(session);
        }
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String validcode, String remember) {
        System.out.println(username + "abc" + password);
        HttpSession session = request.getSession();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //校验验证码
        System.out.println((String)session.getAttribute(LoginInfo.VALIDCODE));
        System.out.println(validcode);
        if (session.getAttribute(LoginInfo.VALIDCODE) != null && !((String)session.getAttribute(LoginInfo.VALIDCODE)).equalsIgnoreCase(validcode)) {
            //生成验证码
            createValidcode(session);
            return LoginStatusEnum.ValidcodeError.getValue();
        }
        //验证账号
        if (userMngService.login(user) == 1) {
            UserInfo userinfo = userMngService.findUserInfoByUsername(user.getUsername());
            UserLocal.set(userinfo);
            System.out.println(UserLocal.get());
            session.removeAttribute(LoginInfo.VALIDCODE);
            //给token
            Cookie cookie = new Cookie(LoginInfo.TOKEN, username);
            if (remember == LoginInfo.REMEMBER) {
                //7天
                cookie.setMaxAge(7*24*3600);
            } else {
                cookie.setMaxAge(-1);
            }
            response.addCookie(cookie);
            return LoginStatusEnum.Success.getValue();
        }
        //生成验证码
        createValidcode(session);
        return LoginStatusEnum.UsernamepasswordError.getValue();
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, String username, String password, String password2, String email) {
        //生成验证码
        createValidcode(request.getSession());
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
            //生成验证码
            createValidcode(session);
            return RegisterStatusEnum.ParamError.getValue();
        }
        if (session.getAttribute(LoginInfo.VALIDCODE) != null && session.getAttribute(LoginInfo.VALIDCODE).equals(validcode)) {
            //生成验证码
            createValidcode(session);
            return RegisterStatusEnum.ValidcodeError.getValue();
        }
//        userMapper.addUser(username, password, email);
        return RegisterStatusEnum.Success.getValue();
    }

    @RequestMapping("/findAccount")
    public String findAccount() {
        return "findAccount";
    }

    @RequestMapping("/doFindAccount")
    public String doFindAccount() {
        return "redirect:/findAccount";
    }

    @GetMapping("/valid-code")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) request.getSession().getAttribute(LoginInfo.VALIDCODE_IMAGE);
        if (image == null) {
            return;
        }
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
    @GetMapping("/refresh-valid-code")
    public void getRefreshCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        createValidcode(session);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) session.getAttribute(LoginInfo.VALIDCODE_IMAGE);
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    private void createValidcode(HttpSession session) {
        Map<String, Object> validcodes = ValidCodeUtil.getValidcode();
        session.setAttribute(LoginInfo.VALIDCODE, validcodes.get(ValidCodeUtil.CODE));
        System.out.println(validcodes.get(ValidCodeUtil.CODE));
        session.setAttribute(LoginInfo.VALIDCODE_IMAGE, validcodes.get(ValidCodeUtil.IMAGE));
    }
}
