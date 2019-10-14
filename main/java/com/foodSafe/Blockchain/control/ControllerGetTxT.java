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
public class ControllerGetTxT {
    @PostMapping("/GetTxT")
    @ResponseBody
    public Map<String , String> GetTxT(@RequestBody Map<String,Object> JsData)
    {
        Map<String , String> TxT = new HashMap<>();
        ArrayList<String> mongoData = new ArrayList<String>();
        String Index = ""+JsData.get("Index");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        mongoData = mongodb.FindDocumentTx("TxT","Index",Index);
        TxT.put("length",mongoData.size()+"");
        for(int i=0;i< mongoData.size();i++)
        {
            TxT.put(i+"",mongoData.get(i));
        }
        System.out.println(TxT);
        return TxT;
    }
}
