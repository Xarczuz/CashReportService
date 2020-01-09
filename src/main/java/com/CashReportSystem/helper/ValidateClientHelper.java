package com.CashReportSystem.helper;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.ForbiddenUI;
import com.CashReportSystem.view.LoginUi;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class ValidateClientHelper {

    UserRepository userRepository;

    public ValidateClientHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateCurrentUser(BeforeEnterEvent event, TokenService tokenService) {

        Cookie[] cookie = VaadinService.getCurrentRequest().getCookies();
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals("token")) {
                String token = cookie[i].getValue();
                try {
                    if (tokenService.validateTokenString(token)) {
                        return userRepository.findByUserName(tokenService.parseToken(token)).get();
                    }
                } catch (NoSuchTokenException e) {
                    event.rerouteTo(ForbiddenUI.class);
                }
            }
        }

        String token = VaadinSession.getCurrent().getAttribute("token").toString();

        if (token != null) {
            try {
                if (tokenService.validateTokenString(token)) {
                    return userRepository.findByUserName(tokenService.parseToken(token)).get();
                }
            } catch (NoSuchTokenException e) {
                event.rerouteTo(ForbiddenUI.class);
            }
        }
        event.rerouteTo(LoginUi.class);
       return new User();
    }
}
