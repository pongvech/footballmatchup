package com.punmac.footballmatchup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping(value = {"/", "index.html"})
    public String index(Model model) {
        model.addAttribute("pageContent", "default/index");
        return "layout";
    }
}
