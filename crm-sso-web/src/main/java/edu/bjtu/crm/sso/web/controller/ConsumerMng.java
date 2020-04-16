package edu.bjtu.crm.sso.web.controller;


import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.Consumer;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.ConsumerMngService;
import edu.bjtu.crm.sso.web.constant.ApiEnum;
import edu.bjtu.crm.sso.web.constant.Bar;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConsumerMng {
    @Resource
    private ConsumerMngService consumerMngService;

    @RequestMapping("/consumerMng")
    public String consumerMng(Model model) {
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("客户信息查询", "consumerSearch()"));
        bars.add(new Bar("添加新客户", "consumerAdd()"));
        model.addAttribute("bars",bars);
        return "consumerMng";
    }

    @RequestMapping("/consumerMng/search")
    @ResponseBody
    public CrmResponse<List<Consumer>> consumerMngQuery(@RequestBody String param, Model model) {
        CrmResponse<List<Consumer>> response = new CrmResponse<>();
        Consumer consumer = JSONObject.parseObject(param, Consumer.class);
        System.out.println(consumer);
        List<Consumer> res = consumerMngService.findConsumerByConsumer(consumer);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(res);
        return response;
    }

    @RequestMapping(value = "/consumerMng/search/{phone}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    public String consumerMngSearchByPhone(@PathVariable("phone") String phone, Model model) {
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("客户信息查询", "consumerSearch()"));
        bars.add(new Bar("添加新客户", "consumerAdd()"));
        model.addAttribute("bars",bars);
        Consumer consumer = consumerMngService.findConsumerByPhone(phone);
        model.addAttribute("consumerSearch", consumer);
        return "consumerMng";
    }

    @RequestMapping(value = "/consumerMng/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse<Consumer> consumerMngUserAdd(@RequestBody String param, Model model) {
        CrmResponse<Consumer> response = new CrmResponse<>();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        Consumer consumer = JSONObject.parseObject(param, Consumer.class);
        System.out.println(consumer);
        if (consumerMngService.addConsumer(consumer) == 1) {
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping(value = "/consumerMng/modify", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse consumerMngModify(@RequestBody String param, Model model) {
        CrmResponse response = new CrmResponse<>();
        Consumer consumer = JSONObject.parseObject(param, Consumer.class);
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        if (consumerMngService.updateConsumer(consumer) == 1){
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping(value = "/consumerMng/modify/{phone}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    public String consumerMngModifyByPhone(@PathVariable("phone") String phone, Model model) {
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("客户信息查询", "consumerSearch()"));
        bars.add(new Bar("添加新客户", "consumerAdd()"));
        model.addAttribute("bars",bars);
        Consumer consumer = consumerMngService.findConsumerByPhone(phone);
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        model.addAttribute("consumerModify", consumer);
        return "consumerMng";
    }

    @RequestMapping(value = "/consumerMng/delete/{phone}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse consumerMngDelete(@PathVariable("phone") String phone) {
        CrmResponse response = new CrmResponse<>();
        if (consumerMngService.deleteConsumerByPhone(phone) == 1){
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }
}
