package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class controller {
    @RequestMapping(value = "/other",method = RequestMethod.GET)
    public String index()
    {
        return "login";
    }
}
