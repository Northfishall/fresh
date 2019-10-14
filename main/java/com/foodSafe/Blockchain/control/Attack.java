package com.foodSafe.Blockchain.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class Attack {
    @PostMapping("/Attack")
    @ResponseBody
    public Map<String , String> GetAVGSP(@RequestBody Map<String,Object> JsData)
    {
        WorkOutData Vici =new WorkOutData(""+JsData.get("Type"),""+JsData.get("Value"));
        Vici.WriteJson();
        Map<String,String> result = new HashMap<>();
        result.put("Result","success");
        return result;
    }
}
