package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.User;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route
public class DashboardUi extends VerticalLayout implements BeforeEnterObserver  {

    private ValidateClientHelper validateClientHelper;
    private TokenService tokenService;
    User user;

    public DashboardUi(TokenService tokenService, ValidateClientHelper validateClientHelper) {
        this.validateClientHelper = validateClientHelper;
        this.tokenService = tokenService;

        MenuBar menuBar = MenuBarComponent.createMenuBar();
        System.out.println(user);
        add(ProfileStatusField.createStatusField(user), menuBar);
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        user = validateClientHelper.validateCurrentUser(event, tokenService);
    }
}
