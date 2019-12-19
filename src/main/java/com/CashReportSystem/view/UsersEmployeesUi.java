package com.CashReportSystem.view;

import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class UsersEmployeesUi extends VerticalLayout {

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public UsersEmployeesUi(EmployeeProfileRepository employeeProfileRepository) {
        MenuBar menuBar = MenuBarComponent.createMenuBar();

        TextField statusField = new TextField();
        statusField.setValue("Admin");
        statusField.setLabel("Profile Status");
        statusField.setEnabled(false);

        // Employees
        List<EmployeeProfile> employeeProfile = employeeProfileRepository.findAll();

        Grid<EmployeeProfile> grid = new Grid<>(EmployeeProfile.class);
        grid.setItems(employeeProfile);

        //grid.removeColumnByKey("id");

        add(statusField,menuBar, grid);
        // The Grid<>(Person.class) sorts the properties and in order to
        // reorder the properties we use the 'setColumns' method.
        grid.setColumns("id","employeeNr","firstName", "lastName", "role","email", "phoneNr");
    }
}