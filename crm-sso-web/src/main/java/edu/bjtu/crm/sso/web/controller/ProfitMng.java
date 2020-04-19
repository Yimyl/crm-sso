package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.Consumer;
import edu.bjtu.crm.sso.domain.model.Product;
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
public class ProfitMng {
    @Resource
    private ProfitMngService profitMngService;

    @Resource
    private UserMngService userMngService;

    @Resource
    private ConsumerMngService consumerMngService;

    @Resource
    private ProductMngService productMngService;

    @RequestMapping("/profitMng")
    public String profitMng(Model model) {
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("添加交易记录", "profitAdd()"));
        bars.add(new Bar("业绩搜索", "profitSearch()"));
        model.addAttribute("bars",bars);
        return "profitMng";
    }

    @RequestMapping("/profitMng/add")
    @ResponseBody
    public CrmResponse profitMngAdd(@RequestBody String param, Model model) {
        CrmResponse response = new CrmResponse();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        Record record = JSONObject.parseObject(param, Record.class);
        if (userMngService.findUserInfoByUsername(record.getUsername()) == null) {
            response.setCode(ApiEnum.PARAM_INVALID.getCode());
            response.setMessage("用户名称错误");
        }
        Consumer consumer = null;
        if (StringUtils.isNotEmpty(record.getPhone()) && (consumer = consumerMngService.findConsumerByPhone(record.getPhone())) == null) {
            response.setCode(ApiEnum.PARAM_INVALID.getCode());
            response.setMessage("客户名称错误");
        }

        if (productMngService.findProductByName(record.getName()) == null) {
            response.setCode(ApiEnum.PARAM_INVALID.getCode());
            response.setMessage("产品名称错误");
        }
        if (profitMngService.addRecord(record) == 1) {
            if (consumer != null) {
                System.out.println(consumer + "" + record.getRealPrice());
                consumerMngService.updateBalance(record.getPhone(), -record.getRealPrice());
                consumerMngService.updateConsume(record.getPhone(), record.getRealPrice());
            }
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping("/profitMng/search")
    @ResponseBody
    public CrmResponse<List<Record>> profitMngSearch(@RequestBody String param, Model model) {
        CrmResponse<List<Record>> response = new CrmResponse<>();
        JSONObject jsonObject = JSONObject.parseObject(param);
        Record record = jsonObject.getObject("record", Record.class);
        Date startTime = jsonObject.getObject("startTime", Date.class);
        Date endTime = jsonObject.getObject("endTime", Date.class);
        List<Record> list = profitMngService.findRecordByRecord(record, startTime, endTime);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(list);
        return response;
    }
}
