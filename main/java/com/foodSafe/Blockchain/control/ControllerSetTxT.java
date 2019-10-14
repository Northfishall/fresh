package com.foodSafe.Blockchain.control;

import com.foodSafe.Blockchain.service.mongodbControl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerSetTxT {
    @PostMapping("/SetTxT")
    @ResponseBody
    public Map<String,String> SetTxT(@RequestBody Map<String,Object> JsData)
    {
        Map<String,String> Result = new HashMap<String,String>();
        String Index = ""+JsData.get("Index");
        String Tx = ""+JsData.get("Tx");
        String address = ""+JsData.get("From");
        mongodbControl mongodb = new mongodbControl("UserDB","admin","password");
        mongodb.insertDocument("TxT", "Index", Index, "Tx", Tx,"From",address);
        //mongodb.insertDocument("TxT", "From", address, "Tx", Tx);
        Result.put("Result","Success");
        return Result;
    }
}
