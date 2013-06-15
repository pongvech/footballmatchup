package com.punmac.footballmatchup.controller;

import com.punmac.footballmatchup.dao.PlayerDao;
import com.punmac.footballmatchup.model.Player;
import com.punmac.footballmatchup.validator.RegisterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private RegisterValidator registerValidator;

    @RequestMapping(value = {"/", "index.html"})
    public String index(Model model) {
        model.addAttribute("pageContent", "default/index");
        return "layout";
    }

    @RequestMapping(value = "register.html")
    public String register(Model model, HttpServletRequest request, @ModelAttribute Player player,
                           BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("Player : {}", player.toString());
            registerValidator.validate(player, bindingResult);
            if(!bindingResult.hasErrors()) {
                playerDao.save(player);
                log.debug("Registered new player (id = {})", player.getId());
            }
        }
        model.addAttribute("pageContent", "default/register");
        return "layout";
    }
}
