package com.CashReportSystem.helper;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.ForbiddenUI;
import com.CashReportSystem.view.LoginUi;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import javax.servlet.http.Cookie;

public class ValidateClientHelper {

    public static void validateCurrentUser(BeforeEnterEvent event, TokenService tokenService) {

        Cookie[] cookie = VaadinService.getCurrentRequest().getCookies();
        for (int i = 0; i < cookie.length; i++) {

            if (cookie[i].getName().equals("token")) {

                String token = cookie[i].getValue();

                try {
                    if (tokenService.validateTokenString(token)) {
                        return;
                    }
                } catch (NoSuchTokenException e) {
                    event.rerouteTo(ForbiddenUI.class);
                }
            }
        }
        if (VaadinSession.getCurrent().getAttribute("token") != null) {
            try {
                if (tokenService.validateTokenString(VaadinSession.getCurrent().getAttribute("token").toString())) {
                    return;
                }
            } catch (NoSuchTokenException e) {
                event.rerouteTo(ForbiddenUI.class);
            }
        }
        event.rerouteTo(LoginUi.class);
    }
}
