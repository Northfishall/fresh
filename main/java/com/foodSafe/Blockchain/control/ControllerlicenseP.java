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
public class ControllerlicenseP {
    @PostMapping("/licenseP")
    @ResponseBody
    public Map<String , String> checkLicense(@RequestBody Map<String,Object> JsData)
    {
        Map<String , String> license = new HashMap<>();

        String Address = ""+JsData.get("Address");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        ArrayList<String> Result = new ArrayList<String>();
        Result = mongodb.FindDocumentAT("CompanyAddressType","Address",Address);
        if(Result.get(0).equals("F"))
        {
            //表示没有对应地址的公司注册
            license.put("Result","no signup");
        }
        else
        {
            if(Result.get(2).equals("P"))
            {
                license.put("Result","success");
            }
            else
            {
                license.put("Result","wrong type");
            }
        }
        return license;
    }
}
