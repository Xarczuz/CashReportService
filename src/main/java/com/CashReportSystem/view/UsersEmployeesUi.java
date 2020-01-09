package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class UsersEmployeesUi extends VerticalLayout implements BeforeEnterObserver {

    private ValidateClientHelper validateClientHelper;
    User user;
    private TokenService tokenService;

    public UsersEmployeesUi(EmployeeProfileRepository employeeProfileRepository, TokenService tokenService, ValidateClientHelper validateClientHelper) {
        this.validateClientHelper = validateClientHelper;
        this.tokenService = tokenService;
        MenuBar menuBar = MenuBarComponent.createMenuBar();

        // Employees
        List<EmployeeProfile> employeeProfile = employeeProfileRepository.findAll();

        Grid<EmployeeProfile> grid = new Grid<>(EmployeeProfile.class);
        grid.setItems(employeeProfile);

        add(ProfileStatusField.createStatusField(user), menuBar, grid);
        grid.setColumns("id", "employeeNr", "firstName", "lastName", "role", "email", "phoneNr");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
       user = validateClientHelper.validateCurrentUser(event, tokenService);
    }
}
