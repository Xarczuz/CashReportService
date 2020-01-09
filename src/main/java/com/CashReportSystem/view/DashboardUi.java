package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route
public class DashboardUi extends VerticalLayout implements BeforeEnterObserver  {

    private TokenService tokenService;

    public DashboardUi(TokenService tokenService) {
        this.tokenService = tokenService;

        MenuBar menuBar = MenuBarComponent.createMenuBar();

        add(ProfileStatusField.createStatusField(), menuBar);
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        ValidateClientHelper.validateCurrentUser(event, tokenService);
    }
}
