package edu.bjtu.crm.sso.web.controller;

import com.alibaba.fastjson.JSONObject;
import edu.bjtu.crm.sso.common.CrmResponse;
import edu.bjtu.crm.sso.domain.model.Product;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.ProductMngService;
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
    private ProductMngService productMngService;

    @RequestMapping("/productMng")
    public String productMng(Model model) {
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("添加交易记录", "profitAdd()"));
        bars.add(new Bar("业绩搜索", "profitSearch()"));
        model.addAttribute("bars",bars);
        return "productMng";
    }

    @RequestMapping("/productMng/search")
    @ResponseBody
    public CrmResponse<List<Product>> productMngQuery(@RequestBody String param, Model model) {
        CrmResponse<List<Product>> response = new CrmResponse<>();
        Product product = JSONObject.parseObject(param, Product.class);
        System.out.println(product);
        List<Product> res = productMngService.findProductByProduct(product);
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMessage(ApiEnum.SUCCESS.getValue());
        response.setResult(res);
        return response;
    }

    @RequestMapping(value = "/productMng/search/{name}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;" +
            "charset=utf-8")
    public String productMngSearchByPhone(@PathVariable("name") String name, Model model) {
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("产品信息查询", "productSearch()"));
        bars.add(new Bar("添加新产品", "productAdd()"));
        model.addAttribute("bars",bars);
        Product product = productMngService.findProductByName(name);
        model.addAttribute("productSearch", product);
        return "productMng";
    }

    @RequestMapping(value = "/productMng/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse<Product> productMngUserAdd(@RequestBody String param, Model model) {
        CrmResponse<Product> response = new CrmResponse<>();
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        Product product = JSONObject.parseObject(param, Product.class);
        System.out.println(product);
        if (productMngService.addProduct(product) == 1) {
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping(value = "/productMng/modify", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CrmResponse productMngModify(@RequestBody String param, Model model) {
        CrmResponse response = new CrmResponse<>();
        Product product = JSONObject.parseObject(param, Product.class);
        if (UserLocal.getIsMng() == 0) {
            response.setCode(ApiEnum.PERMISSION_DENIED.getCode());
            response.setMessage(ApiEnum.PERMISSION_DENIED.getValue());
            return response;
        }
        if (productMngService.updateProduct(product) == 1){
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }

    @RequestMapping(value = "/productMng/modify/{name}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    public String productMngModifyByPhone(@PathVariable("name") String name, Model model) {
        List<Bar> bars = new ArrayList<>();
        bars.add(new Bar("产品信息查询", "productSearch()"));
        bars.add(new Bar("添加新产品", "productAdd()"));
        model.addAttribute("bars",bars);
        Product product = productMngService.findProductByName(name);
        UserInfo userInfo = UserLocal.get();
        model.addAttribute("userinfo",userInfo);
        model.addAttribute("productModify", product);
        return "productMng";
    }

    @RequestMapping(value = "/productMng/delete/{name}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;" +
            "charset=utf-8")
    @ResponseBody
    public CrmResponse productMngDelete(@PathVariable("name") String name) {
        CrmResponse response = new CrmResponse<>();
        if (productMngService.deleteProductByName(name) == 1){
            response.setCode(ApiEnum.SUCCESS.getCode());
            response.setMessage(ApiEnum.SUCCESS.getValue());
            return response;
        }
        response.setCode(ApiEnum.ERROR.getCode());
        response.setMessage(ApiEnum.ERROR.getValue());
        return response;
    }
}
