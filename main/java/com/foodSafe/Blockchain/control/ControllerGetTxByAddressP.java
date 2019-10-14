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
public class ControllerGetTxByAddressP {
    @PostMapping("/GetTxByAddressP")
    @ResponseBody
    public Map<String , String> GetTxByAddressP(@RequestBody Map<String,Object> JsData)
    {
        System.out.println(JsData);
        Map<String , String> TxP = new HashMap<>();
        //Map<String ,String> Tx = new HashMap<>();
        ArrayList<String> mongoData = new ArrayList<String>();
        String Address = ""+JsData.get("Address");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        mongoData = mongodb.FindDocumentTx("TxP","From",Address);
        TxP.put("length",mongoData.size()+"");
        for(int i=0;i< mongoData.size();i++)
        {
            TxP.put(i+"",mongoData.get(i));
        }
        System.out.println(TxP);
        return TxP;
    }
}
