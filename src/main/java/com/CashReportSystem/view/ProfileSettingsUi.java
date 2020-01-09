package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileSettingsFormComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route()
public class ProfileSettingsUi extends VerticalLayout implements BeforeEnterObserver {

    private ValidateClientHelper validateClientHelper;
    private TokenService tokenService;
    User user;

    public ProfileSettingsUi(EmployeeProfileRepository employeeProfileRepository, TokenService tokenService, ValidateClientHelper validateClientHelper) {
        this.validateClientHelper = validateClientHelper;
        this.tokenService = tokenService;

        EmployeeProfile employeeProfile;
        employeeProfile = employeeProfileRepository.findById(1L).get();
        MenuBar menuBar = MenuBarComponent.createMenuBar();

        Component form = ProfileSettingsFormComponent.createSettingsForm(employeeProfile, employeeProfileRepository);

        add(ProfileStatusField.createStatusField(user), menuBar, form);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        user = validateClientHelper.validateCurrentUser(event, tokenService);
    }

}
