package com.punmac.footballmatchup.controller;

import com.punmac.footballmatchup.dao.PlayerDao;
import com.punmac.footballmatchup.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(value = {"/", "index.html"})
    public String index(Model model) {
        Player player = new Player();
        player.setEmail("mac@mac.com");
        playerDao.save(player);
        log.debug("Save : {}", playerDao.findByEmail("mac@mac.com"));
        model.addAttribute("pageContent", "default/index");
        return "layout";
    }
}
