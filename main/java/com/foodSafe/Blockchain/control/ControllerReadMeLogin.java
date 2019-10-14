package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ControllerReadMeLogin
{
    @RequestMapping(value = "/HowToLogin")

    public String transit()
    {
        return "HowToLogin";
    }
}

