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
public class ControllerlicenseF
{
    @PostMapping("/licenseF")
    @ResponseBody
    public Map<String , String> checkLicense(@RequestBody Map<String,Object> JsData)
    {
        Map<String , String> license = new HashMap<>();

        String Address = ""+JsData.get("Address");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        ArrayList<String> Result = new ArrayList<String>();
        //从注册的数据表中进行查询是否存在对应的以太坊账号
        Result = mongodb.FindDocumentAT("CompanyAddressType","Address",Address);
        //不存在对应的以太坊账号
        if(Result.get(0).equals("F"))
        {
            //返回失败 表示没有对应地址的公司注册
            license.put("Result","no signup");
        }
        else
        {
            //存在对应的以太坊账号
            //类型匹配
            if(Result.get(2).equals("F"))
            {
                //返回成功，给予权限
                license.put("Result","success");
            }
            else
            {
                //返回失败 类型不匹配
                license.put("Result","wrong type");
            }
        }
        return license;
    }
}
