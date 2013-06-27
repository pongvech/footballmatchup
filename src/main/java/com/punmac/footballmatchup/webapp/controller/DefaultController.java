package com.punmac.footballmatchup.webapp.controller;

import com.punmac.footballmatchup.core.dao.PlayerDao;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.webapp.bean.form.LoginForm;
import com.punmac.footballmatchup.webapp.util.CookieSessionUtil;
import com.punmac.footballmatchup.webapp.validator.LoginValidator;
import com.punmac.footballmatchup.webapp.validator.RegisterValidator;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController {

    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private UpdateProfileValidator updateProfileValidator;

    @Autowired
    private LoginValidator loginValidator;

    @RequestMapping(value = {"/", "home"})
    public String home() {
        return "forward:/match/home";
    }

    @RequestMapping(value = "register")
    public String register(Model model, HttpServletRequest request, HttpServletResponse response,
                           @ModelAttribute Player player,
                           BindingResult bindingResult,
                           @RequestParam(value = "edit",required = false) boolean editProfile) {

        // Edit profile
        if (editProfile) {
            if (RequestMethod.POST.toString().equals(request.getMethod())){
                updateProfileValidator.validate(player, bindingResult);
                if (!bindingResult.hasErrors()) {
                    playerDao.save(player);
                    model.addAttribute("alert", "<strong>Success!</strong> Profile updated");
                    model.addAttribute("alertCss", "alert alert-success");
                    CookieSessionUtil.deleteLoggedInPlayer(request, response);
                    CookieSessionUtil.createLoggedInCookie(response, playerDao.findById(player.getId()));
                    return "forward:/match/";
                }
            }
            Player loggedInPlayer = CookieSessionUtil.getLoggedInPlayer(request);
            model.addAttribute("pageTitle", "Edit profile");
            model.addAttribute("pageContent", "default/register");
            model.addAttribute("player", loggedInPlayer);
            model.addAttribute("edit", true);
            model.addAttribute("buttonName", "Update");
            return "layout";
        }

        // Register
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("Player : {}", player.toString());
            registerValidator.validate(player, bindingResult);
            if(!bindingResult.hasErrors()) {
                playerDao.save(player);
                log.debug("Registered new player (id = {})", player.getId());
                return "redirect:login.html";
            }
        }
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("pageContent", "default/register");
        model.addAttribute("buttonName", "Register");
        return "layout";
    }

    @RequestMapping(value = "login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("LoginForm : {}", loginForm.toString());
            loginValidator.validate(loginForm, bindingResult);
            if(!bindingResult.hasErrors()) {
                Player player = loginForm.getPlayer();
                log.debug("Player {} login success", player.toString());
                CookieSessionUtil.createLoggedInCookie(response, player);
                log.debug("Cookie value : {}", CookieSessionUtil.getCookie(request, "player"));
                return "redirect:/";
            }
        }
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("pageContent", "default/login");
        return "layout";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieSessionUtil.deleteLoggedInPlayer(request, response);
        return "redirect:/";
    }
}
