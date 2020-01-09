package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.repository.UserRepository;
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

    private TokenService tokenService;

    public ProfileSettingsUi(EmployeeProfileRepository employeeProfileRepository, TokenService tokenService, MenuBarComponent menuBarComponent, UserRepository userRepository) {
        this.tokenService = tokenService;

        String username = tokenService.getUsernameFromToken();
        EmployeeProfile employeeProfile;
        employeeProfile = employeeProfileRepository.findById(1L).get();
        MenuBar menuBar = menuBarComponent.createMenuBar();

        Component form = ProfileSettingsFormComponent.createSettingsForm(employeeProfile, employeeProfileRepository);
        userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), menuBar, form));

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        ValidateClientHelper.validateCurrentUser(event, tokenService);
    }

}
