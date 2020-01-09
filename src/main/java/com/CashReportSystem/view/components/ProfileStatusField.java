package com.CashReportSystem.view.components;

import com.CashReportSystem.model.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinService;

import javax.servlet.http.Cookie;
import javax.swing.*;

public class ProfileStatusField {

    public static Component createStatusField(User user) {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setPadding(false);

        TextField statusField = new TextField();
        TextField statusField2 = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Username: " + user.getUsername());
        statusField2.setValue("Permission: " + user.getPermission());
        statusField.setEnabled(false);
        statusField2.setEnabled(false);

        verticalLayout.add(statusField, statusField2);
        return verticalLayout;
    }
}
