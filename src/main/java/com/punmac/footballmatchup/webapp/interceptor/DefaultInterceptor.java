package com.punmac.footballmatchup.webapp.interceptor;

import com.punmac.footballmatchup.config.FootballMatchUpProperties;
import com.punmac.footballmatchup.core.model.Player;
import com.punmac.footballmatchup.webapp.util.CookieSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(DefaultInterceptor.class);

    @Autowired
    private FootballMatchUpProperties footballMatchUpProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request URI : {}", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        // modelAndView is null in rest base url.
        if(modelAndView == null) {
            return;
        }
        // Does not add model if view is redirect. Spring will not add formatDateTime in query string when submit form.
        if(!modelAndView.getViewName().startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX)) {
            modelAndView.addObject("formatDateTime", footballMatchUpProperties.getFormatDateTime());
        }
        // Check whether player already logged in or not.
        Player player = CookieSessionUtil.getLoggedInPlayer(request);
        if(player != null) { // Player already logged in.
            modelAndView.addObject("loggedInPlayer", player);
        }
    }
}
