package com.CashReportSystem.helper;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.DashboardUi;
import com.CashReportSystem.view.ForbiddenUI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.VaadinService;

import javax.servlet.http.Cookie;

public class ValidateClientHelper {

    public static void validateCurrentUser(BeforeEnterEvent event, TokenService tokenService){

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
        event.rerouteTo(ForbiddenUI.class);
    }
}
