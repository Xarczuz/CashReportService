package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route
public class DashboardUi extends VerticalLayout implements BeforeEnterObserver {

    private TokenService tokenService;

    public DashboardUi(TokenService tokenService, UserRepository userRepository, MenuBarComponent menuBarComponent) {
        this.tokenService = tokenService;

        MenuBar menuBar = menuBarComponent.createMenuBar();
        String username = tokenService.getUsernameFromToken();

        userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), menuBar));

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        ValidateClientHelper.validateCurrentUser(event, tokenService);
    }
}
