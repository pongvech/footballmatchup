package com.punmac.footballmatchup.webapp.controller;

import com.punmac.footballmatchup.core.dao.MatchDao;
import com.punmac.footballmatchup.core.dao.PlayerMatchDao;
import com.punmac.footballmatchup.core.model.Match;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.core.model.PlayerMatch;
import com.punmac.footballmatchup.webapp.typeeditor.DateTimeTypeEditor;
import com.punmac.footballmatchup.webapp.util.CookieSessionUtil;
import com.punmac.footballmatchup.webapp.validator.SaveMatchValidator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "match")
public class MatchController {

    private static final Logger log = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private PlayerMatchDao playerMatchDao;

    @Autowired
    private DateTimeTypeEditor dateTimeTypeEditor;

    @Autowired
    private SaveMatchValidator saveMatchValidator;

    @RequestMapping(value = {"/", "index"})
    public String index(Model model) {
        List<Match> matchList = matchDao.findAll();
        model.addAttribute("matchList", matchList);
        model.addAttribute("pageContent", "match/index");
        return "layout";
    }

    @RequestMapping(value = "info/{matchId}")
    public String info(Model model, HttpServletRequest request, @PathVariable(value = "matchId") String matchId) {
        Player player = CookieSessionUtil.getLoggedInPlayer(request);
        // Check whether player already join this match or not.
        PlayerMatch playerMatch = playerMatchDao.findByPlayerIdAndMatchId(player.getId(), matchId);
        log.debug("PlayerMatch : {}", playerMatch);
        if(playerMatch != null) { // Player already join this match.
            log.debug("Player (username = {}) already join this match (id = {})", player.getUsername(), matchId);
            model.addAttribute("playerMatch", playerMatch);
        }
        Match match = matchDao.findById(matchId);
        List<PlayerMatch> playerMatchList = playerMatchDao.findAllPlayerInMatch(matchId);
        model.addAttribute("match", match);
        model.addAttribute("playerMatchList", playerMatchList);
        model.addAttribute("pageTitle", match.getName());
        model.addAttribute("pageContent", "match/info");
        return "layout";
    }

    @RequestMapping(value = "join/{matchId}")
    public String join(Model model, HttpServletRequest request, @PathVariable(value = "matchId") String matchId) {
        // Init PlayerMatch in here because don't want user to see playerId and matchId in hidden field.
        Player player = CookieSessionUtil.getLoggedInPlayer(request);
        Match match = new Match();
        match.setId(matchId); // Set Id only because Id is reference to playerMatch.player.
        PlayerMatch playerMatch = new PlayerMatch();
        playerMatch.setPlayer(player);
        playerMatch.setMatch(match);
        log.debug("PlayerMatch : {}", playerMatch);
        playerMatchDao.save(playerMatch);
        return "redirect:/match/info/" + matchId;
    }

    @RequestMapping(value = "create")
    public String create(Model model, HttpServletRequest request, @ModelAttribute Match match,
                         BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            // Set creator as loggedIn player
            match.setCreator(CookieSessionUtil.getLoggedInPlayer(request));
            log.debug("Match : {}", match.toString());
            saveMatchValidator.validate(match, bindingResult);
            if(!bindingResult.hasErrors()) {
                matchDao.save(match);
            }
        } else {
            match.setPlayTime(DateTime.now());
        }
        model.addAttribute("pageTitle", "Create Match");
        model.addAttribute("pageContent", "match/save");
        return "layout";
    }

    @RequestMapping(value = "edit/{matchId}")
    public String edit(Model model, HttpServletRequest request,@PathVariable(value = "matchId") String matchId,
                       @ModelAttribute Match match, BindingResult bindingResult) {
        if(RequestMethod.POST.toString().equals(request.getMethod())) {
            log.debug("Match : {}", match.toString());
            saveMatchValidator.validate(match, bindingResult);
            if(!bindingResult.hasErrors()) {
                matchDao.save(match);
            }
        } else {
            match = matchDao.findById(matchId);
            model.addAttribute("match", match);
        }
        model.addAttribute("pageTitle", "Edit Match");
        model.addAttribute("pageContent", "match/save");
        return "layout";
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(DateTime.class, dateTimeTypeEditor);
    }
}