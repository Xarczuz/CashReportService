package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.CustomerProfileRepository;
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
public class UsersCustomersUi extends VerticalLayout implements BeforeEnterObserver {
    private ValidateClientHelper validateClientHelper;
    private TokenService tokenService;
    User user;

    public UsersCustomersUi(CustomerProfileRepository customerProfileRepository, TokenService tokenService, ValidateClientHelper validateClientHelper) {
        this.validateClientHelper = validateClientHelper;
        this.tokenService = tokenService;

        MenuBar menuBar = MenuBarComponent.createMenuBar();

        // Employees
        List<CustomerProfile> customerProfiles = customerProfileRepository.findAll();

        Grid<CustomerProfile> grid = new Grid<>(CustomerProfile.class);
        grid.setItems(customerProfiles);

        add(ProfileStatusField.createStatusField(user), menuBar, grid);
        grid.setColumns("id", "companyName", "orgNr", "firstName", "lastName", "address", "email", "phoneNr");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        user = validateClientHelper.validateCurrentUser(event, tokenService);
    }
}
