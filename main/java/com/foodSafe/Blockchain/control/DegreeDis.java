package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DegreeDis {
    @RequestMapping(value = "/Degree")
/*    void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }*/
    public String transit()
    {
        System.out.println("in");
        return "Degree";
    }
}
