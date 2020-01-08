package com.CashReportSystem.view;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.service.LoginService;
import com.CashReportSystem.service.TokenService;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import javax.servlet.http.Cookie;

@Route
public class LoginUi extends VerticalLayout implements BeforeEnterObserver {

    private TokenService tokenService;
    private LoginService loginService;
    private TokenRepository tokenRepository;

    public LoginUi(LoginService loginService, TokenService tokenService, TokenRepository tokenRepository) {
        this.loginService = loginService;
        this.tokenService = tokenService;
        this.tokenRepository = tokenRepository;

        addLoginForm();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Cookie[] cookie = VaadinService.getCurrentRequest().getCookies();
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals("token")) {
                String token = cookie[i].getValue();
                try {
                    if (tokenService.validateTokenString(token)) {
                        event.rerouteTo(DashboardUi.class);
                    }
                } catch (NoSuchTokenException ignored) {
                }
            }
        }
    }

    private void addLoginForm() {
        LoginForm component = new LoginForm();
        component.setForgotPasswordButtonVisible(false);
        component.addLoginListener(e -> {
            boolean isAuthenticated = loginService.validatePassword(e.getUsername(), e.getPassword());
            if (isAuthenticated) {

                String token = new TokenHelper().tokenBuilder(e.getUsername());
                Token tokenEntity = new Token();
                tokenEntity.setToken(token);
                tokenRepository.save(tokenEntity);
                VaadinService.getCurrentResponse().addCookie(new Cookie("token", token));

                VaadinSession.getCurrent().setAttribute("token", token);
                VaadinSession.getCurrent().getSession().setMaxInactiveInterval(60);
                getUI().ifPresent(ui -> ui.navigate("dashboardui"));

            } else {
                component.setError(true);
            }
        });
        add(component);
    }


}
