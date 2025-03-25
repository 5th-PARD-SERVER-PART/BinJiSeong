package com.pard.server.asg1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ji1Controller {
    @RequestMapping("/Ji1")
    public String ji1() {
        return "Bin.html";
    }
}
