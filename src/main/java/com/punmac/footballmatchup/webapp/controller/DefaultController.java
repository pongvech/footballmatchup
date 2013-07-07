package com.punmac.footballmatchup.webapp.controller;

import com.punmac.footballmatchup.core.dao.PlayerDao;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.webapp.bean.FacebookUser;
import com.punmac.footballmatchup.webapp.bean.form.LoginForm;
import com.punmac.footballmatchup.webapp.bean.form.RegisterForm;
import com.punmac.footballmatchup.webapp.util.CookieSessionUtil;
import com.punmac.footballmatchup.webapp.validator.LoginValidator;
import com.punmac.footballmatchup.webapp.validator.RegisterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @RequestMapping(value = {"/", "home"})
    public String home() {
        return "forward:/match/home";
    }

    @RequestMapping(value = "register")
    public String register(Model model, HttpServletRequest request, @ModelAttribute RegisterForm registerForm,
                           BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("Registering player, Player = {}", registerForm.toString());
            registerValidator.validate(registerForm, bindingResult);
            if(!bindingResult.hasErrors()) {
                // Convert RegisterFrom to Player, DB will not store confirmPassword.
                Player player = new Player();
                player.setEmail(registerForm.getEmail());
                player.setUsername(registerForm.getUsername());
                player.setPassword(registerForm.getPassword());
                playerDao.save(player);
                log.debug("Registered new player (username = {})", registerForm.getUsername());
                return "redirect:/login";
            }
        }
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("pageContent", "default/register");
        return "layout";
    }

    @RequestMapping(value = "login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("Logging In, LoginForm = {}", loginForm.toString());
            loginValidator.validate(loginForm, bindingResult);
            if(!bindingResult.hasErrors()) {
                Player player = loginForm.getPlayer();
                log.debug("Player {} login success", player.getUsername());
                CookieSessionUtil.createLoggedInCookie(response, player);
                return "redirect:/";
            }
        }
        model.addAttribute("pageTitle", "Sign In");
        model.addAttribute("pageContent", "default/login");
        return "layout";
    }

    @RequestMapping(value = "login/fb")
    public String loginFB(Model model, HttpServletRequest request) {
        if(request.getParameter("error") != null) { // If fb login not success.
            return "redirect:/";
        }

        String code = request.getParameter("code");
        log.debug("code = {}", code);

        String token;
        String queryStringToken = restTemplate.getForObject("https://graph.facebook.com/oauth/access_token?" +
                "client_id=517466198319051" +
                "&redirect_uri=" + request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/login/fb" +
                "&client_secret=aaefacaf6a650c7b893f130044b46269" +
                "&code=" + code, String.class);
        log.debug("jsonToken = {}", queryStringToken);
        String[] splitQueryStringToken = queryStringToken.split("&");
        String[] splitToken = splitQueryStringToken[0].split("=");
        token = splitToken[1];
        log.debug("token = {}", token);

        FacebookUser facebookUser = restTemplate.getForObject("https://graph.facebook.com/me?fields=name,username,email&access_token=" + token, FacebookUser.class);
        log.debug("user info = {}", facebookUser.toString());
        model.addAttribute("pageContent", "test");
        return "layout";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieSessionUtil.deleteLoggedInPlayer(request, response);
        return "redirect:/";
    }

    @InitBinder
    private void binder(WebDataBinder binder) {
        // StringTrimmerEditor will trim all String when we submit form.
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
