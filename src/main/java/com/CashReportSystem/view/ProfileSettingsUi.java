package com.CashReportSystem.view;

import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileSettingsFormComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route()
public class ProfileSettingsUi extends VerticalLayout {

    public ProfileSettingsUi(EmployeeProfileRepository employeeProfileRepository) {
        EmployeeProfile employeeProfile;
        employeeProfile = employeeProfileRepository.findById(1L).get();
        MenuBar menuBar = MenuBarComponent.createMenuBar();

        String permission = "admin";
        TextField statusField = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Admin");
        statusField.setEnabled(false);

        Component form = ProfileSettingsFormComponent.createSettingsForm(employeeProfile, employeeProfileRepository);

        add(statusField, menuBar, form);
    }

}
