package com.CashReportSystem.view;

import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.service.LoginService;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class LoginUi extends VerticalLayout {

    private final LoginService loginService;

    public LoginUi(LoginService loginService) {
        this.loginService = loginService;
        LoginForm component = new LoginForm();
        component.setForgotPasswordButtonVisible(false);
        component.addLoginListener(e -> {
            boolean isAuthenticated = false;
            try {
                isAuthenticated = this.loginService.validatePassword(e.getUsername(), e.getPassword());
                if (isAuthenticated) {
                    navigateToMainPage();
                } else {
                    component.setError(true);
                }
            } catch (NoSuchUserException ex) {
                component.setError(true);
            }

            System.out.println(e.getPassword() + " " + e.getUsername());
        });
        add(component);

    }

    private void navigateToMainPage() {
        getUI().ifPresent(ui -> ui.navigate("dashboardui"));
    }
}
