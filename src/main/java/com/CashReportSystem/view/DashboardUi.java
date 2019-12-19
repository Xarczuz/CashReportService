package com.CashReportSystem.view;

import com.CashReportSystem.view.components.MenuBarComponent;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class DashboardUi extends VerticalLayout {

    public DashboardUi() {

        MenuBar menuBar = MenuBarComponent.createMenuBar();

        TextField statusField = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Admin");
        statusField.setEnabled(false);

        add(statusField, menuBar);
    }
}
