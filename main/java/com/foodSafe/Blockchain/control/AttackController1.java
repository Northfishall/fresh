package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AttackController1 {
    @RequestMapping(value = "/Attack1")
    public String transit()
    {
        System.out.println("in");
        return "Attack1";
    }
}
