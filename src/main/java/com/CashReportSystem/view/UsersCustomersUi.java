package com.CashReportSystem.view;

import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class UsersCustomersUi extends VerticalLayout {

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public UsersCustomersUi(CustomerProfileRepository customerProfileRepository) {
        MenuBar menuBar = MenuBarComponent.createMenuBar();

        TextField statusField = new TextField();
        statusField.setValue("Admin");
        statusField.setLabel("Profile Status");
        statusField.setEnabled(false);

        // Employees
        List<CustomerProfile> customerProfiles = customerProfileRepository.findAll();

        Grid<CustomerProfile> grid = new Grid<>(CustomerProfile.class);
        grid.setItems(customerProfiles);

        //grid.removeColumnByKey("id");

        add(statusField,menuBar, grid);
        // The Grid<>(Person.class) sorts the properties and in order to
        // reorder the properties we use the 'setColumns' method.
        grid.setColumns("id","companyName","orgNr","firstName", "lastName", "address","email", "phoneNr");
    }
}
