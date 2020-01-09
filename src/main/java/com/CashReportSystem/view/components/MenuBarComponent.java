package com.CashReportSystem.view.components;

import com.CashReportSystem.repository.TokenRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class MenuBarComponent {
    private TokenRepository tokenRepository;

    public MenuBarComponent(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Profile", e -> UI.getCurrent().navigate("profilesettingsui"));

        MenuItem users = menuBar.addItem("Users");
        SubMenu usersSubMenu = users.getSubMenu();
        usersSubMenu.addItem("Employee's", e -> UI.getCurrent().navigate("usersemployeesui"));
        usersSubMenu.addItem("Customer's", e -> UI.getCurrent().navigate("userscustomersui"));

        menuBar.addItem("Reports", e -> UI.getCurrent().navigate("reportsui"));

        menuBar.addItem("Sign Out", e -> {
            deleteCookie();
            UI.getCurrent().navigate("loginui");
        });
        return menuBar;
    }

    public void deleteCookie() {
        Cookie[] cookie = VaadinService.getCurrentRequest().getCookies();
        for (Cookie value : cookie) {
            if (value.getName().equals("token")) {
                tokenRepository.findByToken(value.getValue()).ifPresent(token -> tokenRepository.delete(token));
                value.setValue("");
                value.setPath("/");
                value.setMaxAge(0);
                VaadinService.getCurrentResponse().addCookie(value);
                VaadinSession.getCurrent().close();
            }
        }

    }

}
