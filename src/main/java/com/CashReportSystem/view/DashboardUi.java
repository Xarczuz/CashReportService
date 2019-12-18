package com.CashReportSystem.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route
public class DashboardUi extends VerticalLayout {

    public DashboardUi() {

        MenuBar menuBar = new MenuBar();
        Text selected = new Text("");
        Div message = new Div(new Text("Selected: "), selected);

        MenuItem profile = menuBar.addItem("Profile");
        SubMenu profileSubMenu = profile.getSubMenu();
        MenuItem settings = profileSubMenu.addItem("Settings");

        MenuItem users = menuBar.addItem("Users");
        SubMenu usersSubMenu = users.getSubMenu();
        Optional<UI> getUI = getUI();
        usersSubMenu.addItem("Employee's", e -> UI.getCurrent().navigate("usersemployeesui"));
        usersSubMenu.addItem("Customer's");

        MenuItem reports = menuBar.addItem("Reports");
        SubMenu reportsSubMenu = reports.getSubMenu();
        reportsSubMenu.addItem("Report's");

        menuBar.addItem("Sign Out", e -> selected.setText("Sign Out"));

        TextField statusField = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Admin");
        statusField.setEnabled(false);

        add(statusField,menuBar);
    }
}
