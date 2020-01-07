package com.CashReportSystem.view.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinService;

import javax.servlet.http.Cookie;
import javax.swing.*;

public class ProfileStatusField {

    public static Component createStatusField() {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setPadding(false);

        TextField statusField = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Username" + "Admin");
        statusField.setEnabled(false);

        verticalLayout.add(statusField);
        return verticalLayout;
    }
}
