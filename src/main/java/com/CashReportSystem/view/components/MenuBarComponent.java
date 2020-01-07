package com.CashReportSystem.view.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.server.VaadinService;

import javax.servlet.http.Cookie;

public class MenuBarComponent {
    public static MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Profile", e -> UI.getCurrent().navigate("profilesettingsui"));

        MenuItem users = menuBar.addItem("Users");
        SubMenu usersSubMenu = users.getSubMenu();
        usersSubMenu.addItem("Employee's", e -> UI.getCurrent().navigate("usersemployeesui"));
        usersSubMenu.addItem("Customer's", e -> UI.getCurrent().navigate("userscustomersui"));

        menuBar.addItem("Reports", e -> UI.getCurrent().navigate("reportsui"));

        menuBar.addItem("Sign Out", e -> {
            MenuBarComponent.deleteCookie();
            UI.getCurrent().navigate("loginui");
        });
        return menuBar;
    }


    public static void deleteCookie() {
        Cookie[] cookie = VaadinService.getCurrentRequest().getCookies();
        for (Cookie value : cookie) {
            if (value.getName().equals("token")) {
                value.setValue("");
                value.setPath("/");
                value.setMaxAge(0);
                VaadinService.getCurrentResponse().addCookie(value);
            }
        }

    }

}
