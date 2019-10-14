package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public String transit()
    {
        System.out.println("in");
        return "index";
    }
}
