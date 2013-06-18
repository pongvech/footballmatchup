package com.punmac.footballmatchup.controller;

import com.punmac.footballmatchup.model.Match;
import com.punmac.footballmatchup.typeeditor.DateTimeTypeEditor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "match")
public class MatchController {

    @Autowired
    private DateTimeTypeEditor dateTimeTypeEditor;

    @RequestMapping(value = {"/", "index"})
    public String index(Model model) {
        model.addAttribute("pageContent", "match/index");
        return "layout";
    }

    @RequestMapping(value = "create")
    public String create(Model model, HttpServletRequest request, @ModelAttribute Match match,
                         BindingResult bindingResult) {
        model.addAttribute("pageTitle", "Create Match");
        model.addAttribute("pageContent", "match/save");
        return "layout";
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(DateTime.class, dateTimeTypeEditor);
    }
}
