package com.punmac.footballmatchup.interceptor;

import com.punmac.footballmatchup.model.Player;
import com.punmac.footballmatchup.util.CookieSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(DefaultInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request URI : {}", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        Player player = CookieSessionUtil.getLoggedInPlayer(request);
        if(player != null) { // Player have logged in.
            modelAndView.getModelMap().addAttribute("loggedInPlayer", player);
        }
    }
}
