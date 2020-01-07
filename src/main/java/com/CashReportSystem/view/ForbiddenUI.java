package com.CashReportSystem.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class ForbiddenUI extends VerticalLayout {
    public ForbiddenUI() {
        TextField textField = new TextField();
        textField.setValue("Forbidden");
        add(textField);
    }
}
