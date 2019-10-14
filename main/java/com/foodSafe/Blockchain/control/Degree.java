package com.foodSafe.Blockchain.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Degree {
    @PostMapping("/Degree")
    @ResponseBody
    public Map<String , String> Degree(@RequestBody Map<String,Object> JsData)
    {
        WorkOutData V = new WorkOutData(""+JsData.get("Type"),""+JsData.get("Value"));
        int[] ListDegree = V.DegreeList();
        V.WriteDegreeData(ListDegree);
        Map<String , String> Result = new HashMap<>();
        Result.put("Result","success");
        return Result;
    }
}
