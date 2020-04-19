package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.*;
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

    @RequestMapping("/statementMng/search/day/user")
    @ResponseBody
    public CrmResponse<List<RecordDay>> statementMngSearchDayUser(@RequestBody String param, Model model) {
        CrmResponse<List<RecordDay>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordDay> list = statementMngService.findRecordDayUserByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping("/statementMng/search/day/consumer")
    @ResponseBody
    public CrmResponse<List<RecordDay>> statementMngSearchDayConsumer(@RequestBody String param, Model model) {
        CrmResponse<List<RecordDay>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordDay> list = statementMngService.findRecordDayConsumerByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping("/statementMng/search/day/product")
    @ResponseBody
    public CrmResponse<List<RecordDay>> statementMngSearchDayProduct(@RequestBody String param, Model model) {
        CrmResponse<List<RecordDay>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordDay> list = statementMngService.findRecordDayProductByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }


    @RequestMapping("/statementMng/search/month/user")
    @ResponseBody
    public CrmResponse<List<RecordMonth>> statementMngSearchMonthUser(@RequestBody String param, Model model) {
        CrmResponse<List<RecordMonth>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordMonth> list = statementMngService.findRecordMonthUserByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping("/statementMng/search/month/consumer")
    @ResponseBody
    public CrmResponse<List<RecordMonth>> statementMngSearchMonthConsumer(@RequestBody String param, Model model) {
        CrmResponse<List<RecordMonth>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordMonth> list = statementMngService.findRecordMonthConsumerByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping("/statementMng/search/month/product")
    @ResponseBody
    public CrmResponse<List<RecordMonth>> statementMngSearchMonthProduct(@RequestBody String param, Model model) {
        CrmResponse<List<RecordMonth>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordMonth> list = statementMngService.findRecordMonthProductByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }




    @RequestMapping("/statementMng/search/year/user")
    @ResponseBody
    public CrmResponse<List<RecordYear>> statementMngSearchYearUser(@RequestBody String param, Model model) {
        CrmResponse<List<RecordYear>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordYear> list = statementMngService.findRecordYearUserByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping("/statementMng/search/year/consumer")
    @ResponseBody
    public CrmResponse<List<RecordYear>> statementMngSearchYearConsumer(@RequestBody String param, Model model) {
        CrmResponse<List<RecordYear>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordYear> list = statementMngService.findRecordYearConsumerByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }

    @RequestMapping("/statementMng/search/year/product")
    @ResponseBody
    public CrmResponse<List<RecordYear>> statementMngSearchYearProduct(@RequestBody String param, Model model) {
        CrmResponse<List<RecordYear>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<RecordYear> list = statementMngService.findRecordYearProductByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }
}
