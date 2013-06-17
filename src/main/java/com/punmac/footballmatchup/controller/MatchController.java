package com.punmac.footballmatchup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "match")
public class MatchController {

    @RequestMapping(value = {"/", "index"})
    public String index(Model model) {
        model.addAttribute("pageContent", "match/index");
        return "layout";
    }

    @RequestMapping(value = "create")
    public String create(Model model) {
        model.addAttribute("pageContent", "match/save");
        return "layout";
    }
}
