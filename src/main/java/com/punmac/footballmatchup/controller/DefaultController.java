package com.punmac.footballmatchup.controller;

import com.punmac.footballmatchup.bean.form.LoginForm;
import com.punmac.footballmatchup.dao.PlayerDao;
import com.punmac.footballmatchup.dao.PlayerMatchDao;
import com.punmac.footballmatchup.model.Player;
import com.punmac.footballmatchup.util.CookieSessionUtil;
import com.punmac.footballmatchup.validator.LoginValidator;
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
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController {

    private static final Logger log = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private PlayerMatchDao playerMatchDao;

    @RequestMapping(value = {"/", "index.html"})
    public String index(Model model, HttpServletRequest request) {
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
                return "redirect:login.html";
            }
        }
        model.addAttribute("pageContent", "default/register");
        return "layout";
    }

    @RequestMapping(value = "login.html")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("LoginForm : {}", loginForm.toString());
            loginValidator.validate(loginForm, bindingResult);
            if(!bindingResult.hasErrors()) {
                Player player = loginForm.getPlayer();
                log.debug("Player {} login success", player.toString());
                if(loginForm.isRememberMe()) { // Create cookie.
                    CookieSessionUtil.createLoggedInCookie(response, player);
                    log.debug("Cookie value : {}", CookieSessionUtil.getCookie(request, "player"));
                } else { // Create session.
                    CookieSessionUtil.createLoggedInSession(request, player);
                    log.debug("Session value : {}", request.getSession().getAttribute("player"));
                }
                return "redirect:index.html";
            }
        }
        model.addAttribute("pageContent", "default/login");
        return "layout";
    }

    @RequestMapping(value = "logout.html")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieSessionUtil.deleteLoggedInPlayer(request, response);
        return "redirect:index.html";
    }
}
