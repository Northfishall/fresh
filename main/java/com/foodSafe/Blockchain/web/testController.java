package com.foodSafe.Blockchain.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodSafe.Blockchain.service.mongodbControl;

@RestController
@RequestMapping("/layui")
public class testController {
    @RequestMapping("/index")
    public String demo()
    {
        return "layui/index";
    }

    @RequestMapping("/Test")
    public String Test()
    {
        return "Test";
    }
}
