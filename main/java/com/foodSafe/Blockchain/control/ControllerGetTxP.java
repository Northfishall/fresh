package com.foodSafe.Blockchain.control;

import com.foodSafe.Blockchain.service.mongodbControl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerGetTxP {
    @PostMapping("/GetTxP")
    @ResponseBody
    /*    void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }*/
    //input data is [Index:index]
    public Map<String , String> GetTxP(@RequestBody Map<String,Object> JsData)
    {
        System.out.println(JsData);
        Map<String , String> TxP = new HashMap<>();
        //Map<String ,String> Tx = new HashMap<>();
        ArrayList<String> mongoData = new ArrayList<String>();

        String Index = ""+JsData.get("Index");

        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        mongoData = mongodb.FindDocumentTx("TxP","Index",Index);
        TxP.put("length",mongoData.size()+"");
        for(int i=0;i< mongoData.size();i++)
        {
            TxP.put(i+"",mongoData.get(i));
        }
        System.out.println(TxP);
        return TxP;
    }
}
