package com.foodSafe.Blockchain.control;

import com.foodSafe.Blockchain.service.mongodbControl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerSignup
{
    @PostMapping("/signup")
    @ResponseBody
    public Map<String,String> signup(@RequestBody Map<String,Object> JsData)
    {
        //String exist;
        Map<String,String> Result = new HashMap<String,String>();
        String CompanyName = ""+JsData.get("CompanyName");
        String EthAddress = ""+JsData.get("EthAddress");
        String InviteCode = ""+JsData.get("InviteCode");
        String CompanyType = ""+JsData.get("CompanyType");
        //跟数据库建立连接
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        //查询是否存在该邀请码
        String exist = mongodb.FindDocumentIC("InviteCode","InviteCode",InviteCode);
        if(exist.equals("success"))
        {
            //插入注册信息
            mongodb.insertDocument("CompanyAddressType","CompanyName",CompanyName,"Address",EthAddress,"Type",CompanyType);
            //删除对应的注册码
            mongodb.DelectDocument("InviteCode","InviteCode",InviteCode);
            //设置返回值，成功注册
            Result.put("Result","success");
        }
        else
        {
            Result.put("Result","Error");
        }
        System.out.println(Result);
        return Result;
    }
}
