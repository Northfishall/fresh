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
public class ControllerGetTxByAddressT
{
    /*通过公司名称读取ETH Address 以及 对应Address的交易凭证 P/T/F同理*/
    @PostMapping("/GetTxByAddressT")
    @ResponseBody
    public Map<String , String> GetTxByAddressT(@RequestBody Map<String,Object> JsData)
    {
        System.out.println(JsData);
        Map<String , String> TxT = new HashMap<>();
        //Map<String ,String> Tx = new HashMap<>();
        ArrayList<String> mongoData = new ArrayList<String>();
        String Address = ""+JsData.get("Address");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        mongoData = mongodb.FindDocumentTx("TxT","From",Address);
        TxT.put("length",mongoData.size()+"");
        for(int i=0;i< mongoData.size();i++)
        {
            TxT.put(i+"",mongoData.get(i));
        }
        System.out.println(TxT);
        return TxT;
    }
}
