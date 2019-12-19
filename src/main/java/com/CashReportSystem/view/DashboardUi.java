package com.CashReportSystem.view;

import com.CashReportSystem.view.components.MenuBarComponent;
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

        MenuBar menuBar = MenuBarComponent.createMenuBar();

        TextField statusField = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Admin");
        statusField.setEnabled(false);

        add(statusField,menuBar);
    }
}
