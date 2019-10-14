package com.foodSafe.Blockchain.control;

import com.foodSafe.Blockchain.service.mongodbControl;
import com.foodSafe.Blockchain.control.WorkOutData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AVGshortestPath {
    @PostMapping("/AVGshortestPath")
    @ResponseBody
    public Map<String , String> GetAVGSP(@RequestBody Map<String,Object> JsData)
    {
        WorkOutData V = new WorkOutData(""+JsData.get("Type"),""+JsData.get("Value"));
        int [][] Dis= V.ShortestPath();
        double length = V.AvgShortest(Dis);
        System.out.println(length);
        Map<String , String> Result = new HashMap<>();
        if(length>4)
        {
            Result.put("Length","非连通图");
        }
        else
        {
            Result.put("Length",length+"");
        }


        return Result;
    }
}
