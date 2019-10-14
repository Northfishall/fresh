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
public class ControllerGetTxF {
    @PostMapping("/GetTxF")
    @ResponseBody
    public Map<String , String> GetTxF(@RequestBody Map<String,Object> JsData)
    {
        Map<String , String> TxF = new HashMap<>();
        ArrayList<String> mongoData = new ArrayList<String>();
        String Index = ""+JsData.get("Index");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        mongoData = mongodb.FindDocumentTx("TxF","Index",Index);
        TxF.put("length",mongoData.size()+"");
        for(int i=0;i< mongoData.size();i++)
        {
            TxF.put(i+"",mongoData.get(i));
        }
        System.out.println(TxF);
        return TxF;
    }
}
