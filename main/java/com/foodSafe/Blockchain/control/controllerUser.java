package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.foodSafe.Blockchain.service.mongodbControl;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class controllerUser {
    @PostMapping("/user")
    @ResponseBody
    public Map<String , String> getAT(@RequestBody Map<String,Object> JsData)
    {
        System.out.println(JsData);
        ArrayList<String> Result = new ArrayList<String>();
        Map<String ,String> dictionary = new HashMap<>();
        String CompanyAddress = null;
        String companyName = ""+JsData.get("CompanyName");
        System.out.println(companyName);
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        //mongodb.createCollection("companyAddress");
        //mongodb.insertDocument("companyAddress",companyName,"0x6BCB00f582f6e57d3539fc5e99Fe79a4bB6FCbcD");
        Result = mongodb.FindDocumentAT("CompanyAddressType","CompanyName",companyName);
        Map<String , String> Tx = new HashMap<>();
        //将两个部分合为一体 优化 效率 获取Address&&Type 以及通过Type获取Tx的步骤合并
        if(Result.get(0)=="T")
        {
            System.out.println("New User");
            Tx.put("Result","T");
            System.out.println(Tx);
            System.out.println(Result.get(2));
            if(Result.get(2).equals("P"))
            {

                ArrayList<String> mongoData = new ArrayList<String>();
                mongoData = mongodb.FindDocumentTx("TxP","From",Result.get(1));
                Tx.put("length",mongoData.size()+"");
                for(int i=0;i< mongoData.size();i++)
                {
                    Tx.put(i+"",mongoData.get(i));
                }
                System.out.println(Tx);
            }
            else if (Result.get(2).equals("T"))
            {
                ArrayList<String> mongoData = new ArrayList<String>();
                mongoData = mongodb.FindDocumentTx("TxT","From",Result.get(1));
                Tx.put("length",mongoData.size()+"");
                for(int i=0;i< mongoData.size();i++)
                {
                    Tx.put(i+"",mongoData.get(i));
                }
                System.out.println(Tx);
            }
            else if (Result.get(2).equals("F"))
            {

                ArrayList<String> mongoData = new ArrayList<String>();
                mongoData = mongodb.FindDocumentTx("TxF","From",Result.get(1));
                Tx.put("length",mongoData.size()+"");
                System.out.println(mongoData);
                for(int i=0;i< mongoData.size();i++)
                {
                    Tx.put(i+"",mongoData.get(i));
                }
                System.out.println(Tx);
            }
/*            dictionary.put("Result","T");
            dictionary.put("Address",Result.get(1));
            dictionary.put("Type",Result.get(2));*/
            Tx.put("Type",Result.get(2));
            Tx.put("Address",Result.get(1));
        }
        else
        {
            //dictionary.put("Result","F");
            Tx.put("Result","F");
        }


        System.out.println(Tx);
        return Tx;
    }
}
