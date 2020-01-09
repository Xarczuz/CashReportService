package com.CashReportSystem.view.components;

import com.CashReportSystem.model.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ProfileStatusField {

    public static Component createStatusField(User user) {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setPadding(false);

        TextField statusField = new TextField();
        statusField.setLabel("Profile Status");
        statusField.setValue("Username: " + user.getUsername());
        statusField.setEnabled(false);

        TextField statusField2 = new TextField();
        statusField2.setValue("Permission:" + user.getPermission());
        statusField2.setEnabled(false);

        verticalLayout.add(statusField, statusField2);
        return verticalLayout;
    }
}
