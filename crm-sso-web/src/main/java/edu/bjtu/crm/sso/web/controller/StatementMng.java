package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.Consumer;
import edu.bjtu.crm.sso.domain.model.Record;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.ConsumerMngService;
import edu.bjtu.crm.sso.service.ProductMngService;
import edu.bjtu.crm.sso.service.ProfitMngService;
import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.web.constant.ApiEnum;
import edu.bjtu.crm.sso.web.constant.Bar;
import edu.bjtu.crm.sso.web.util.UserLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StatementMng {
    @Resource
    private ProfitMngService statementMngService;

    @Resource
    private UserMngService userMngService;

    @Resource
    private ConsumerMngService consumerMngService;

    @Resource
    private ProductMngService productMngService;

    @RequestMapping("/statementMng")
    public String statementMng(Model model) {
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("业绩报表", "statementSearch()"));
        model.addAttribute("bars",bars);
        return "statementMng";
    }

    @RequestMapping("/statementMng/searchUser")
    @ResponseBody
    public CrmResponse<List<Record>> statementMngSearch(@RequestBody String param, Model model) {
        CrmResponse<List<Record>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<Record> list = statementMngService.findRecordByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }
}
