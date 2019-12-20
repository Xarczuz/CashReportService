package com.CashReportSystem.view.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;

public class MenuBarComponent {
    public static com.vaadin.flow.component.menubar.MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();

        MenuItem profile = menuBar.addItem("Profile", e -> UI.getCurrent().navigate("profilesettingsui"));
        //SubMenu profileSubMenu = profile.getSubMenu();

        MenuItem users = menuBar.addItem("Users");
        SubMenu usersSubMenu = users.getSubMenu();
        usersSubMenu.addItem("Employee's", e -> UI.getCurrent().navigate("usersemployeesui"));
        usersSubMenu.addItem("Customer's", e -> UI.getCurrent().navigate("userscustomersui"));

        MenuItem reports = menuBar.addItem("Reports", e -> UI.getCurrent().navigate("reportsui"));
        //SubMenu reportsSubMenu = reports.getSubMenu();

        menuBar.addItem("Sign Out");
        return menuBar;
    }
}
