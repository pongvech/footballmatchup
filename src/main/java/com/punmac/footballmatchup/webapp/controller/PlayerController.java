package com.punmac.footballmatchup.webapp.controller;

import com.punmac.footballmatchup.core.dao.PlayerDao;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.webapp.util.CookieSessionUtil;
import com.punmac.footballmatchup.webapp.validator.UpdateProfileValidator;
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
import javax.servlet.http.HttpServletResponse;

import static org.springframework.data.mongodb.core.query.Update.update;

@Controller
@RequestMapping(value = "player")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private UpdateProfileValidator updateProfileValidator;

    /**
     * Right now, player can update username only.
     */
    @RequestMapping(value = "edit")
    public String edit(Model model,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       @ModelAttribute Player player,
                       BindingResult bindingResult) {
        Player loggedInPlayer = CookieSessionUtil.getLoggedInPlayer(request);
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("Player {} is editing profile, Player : {}", player.getUsername(),player.toString());
            updateProfileValidator.validate(player, bindingResult);
            if(!bindingResult.hasErrors()) {
                playerDao.updateById(loggedInPlayer.getId(), update("username", player.getUsername()));
                // Delete current cookie.
                CookieSessionUtil.deleteLoggedInPlayer(request, response);
                // Create new cookie.
                CookieSessionUtil.createLoggedInCookie(response, player);
                return "redirect:/player/edit";
            }
        } else {
            player = playerDao.findById(loggedInPlayer.getId());
        }
        model.addAttribute("player", player);
        model.addAttribute("pageTitle", "Edit Profile");
        model.addAttribute("pageContent", "player/edit");
        return "layout";
    }
}
