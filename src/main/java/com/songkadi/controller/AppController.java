package com.songkadi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String home(ModelMap modal) {
        modal.addAttribute("title", "AngularJs Tutorial");
        return "index";
    }
}
