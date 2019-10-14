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
public class ControllerAddressIndex
{
    @PostMapping("/AI")
    @ResponseBody
    public Map<String , String> getAT(@RequestBody Map<String,Object> JsData)
    {
        System.out.println(JsData);
        ArrayList<String> Result = new ArrayList<String>();
        //求出重合tx
        Map<String ,String> dictionary = new HashMap<>();
        Map<String , String> Tx = new HashMap<>();
        String companyName = ""+JsData.get("CompanyName");
        String Index = ""+JsData.get("Index");
        System.out.println(companyName);
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        //通过公司名称查询Address
        Result = mongodb.FindDocumentAT("CompanyAddressType","CompanyName",companyName);
        System.out.println("Result");
        System.out.println(Result);
        //将两个部分合为一体 优化 效率 获取Address&&Type 以及通过Address获取Tx的步骤合并
        //查询公司名称存在
        if(Result.get(0).equals("T"))
        {
            System.out.println("New User");
            //设置返回结果 给前端判断
            Tx.put("Result","T");
            System.out.println(Result.get(2));
            //根据公司种类分别进行操作
            if(Result.get(2).equals("P"))
            {

                ArrayList<String> mongoData = new ArrayList<String>();
                mongoData = mongodb.FindDocumentTx("TxP","From",Result.get(1),"Index",Index);
                //设置Tx的个数 方便前端处理 将下标作为KEY
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
                mongoData = mongodb.FindDocumentTx("TxT","From",Result.get(1),"Index",Index);
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
                mongoData = mongodb.FindDocumentTx("TxF","From",Result.get(1),"Index",Index);
                Tx.put("length",mongoData.size()+"");
                System.out.println(mongoData);
                for(int i=0;i< mongoData.size();i++)
                {
                    Tx.put(i+"",mongoData.get(i));
                }
                System.out.println(Tx);
            }
            Tx.put("Type",Result.get(2));
            Tx.put("Address",Result.get(1));
/*            dictionary.put("Result","T");
            dictionary.put("Address",Result.get(1));
            dictionary.put("Type",Result.get(2));*/
        }
        else
        {
            //dictionary.put("Result","F");
            //如果没有结果则会设置
            Tx.put("Result","F");
        }


        System.out.println(Tx);
        return Tx;
    }
}
