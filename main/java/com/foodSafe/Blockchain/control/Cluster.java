package com.foodSafe.Blockchain.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Cluster {
    @PostMapping("/Cluster")
    @ResponseBody
    public Map<String , String> GetClu(@RequestBody Map<String,Object> JsData)
    {
        WorkOutData V = new WorkOutData(JsData.get("Type")+"",JsData.get("Value")+"");
        double Clu = V.Clustering();
        System.out.println(Clu);
        Map<String , String> Result = new HashMap<>();
        Result.put("Clu",Clu+"");
        return Result;
    }


}
