package com.foodSafe.Blockchain.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class controllerLogin
{
    @RequestMapping(value = "/login")
/*    void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }*/
    public String login()
    {
        return "login";
    }
}
