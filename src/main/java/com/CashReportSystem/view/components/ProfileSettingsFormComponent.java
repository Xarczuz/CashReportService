package com.CashReportSystem.view.components;

import com.CashReportSystem.model.EmployeeProfile;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.Optional;
import java.util.stream.Collectors;

public class ProfileSettingsFormComponent {
    public static Component createSettingsForm(EmployeeProfile employeeProfile) {
        FormLayout layoutWithBinder = new FormLayout();
        Binder<EmployeeProfile> binder = new Binder<>();

// The object that will be edited
        EmployeeProfile profileBeingEdited = employeeProfile;

// Create the fields

        TextField role = new TextField();
        role.setValue(profileBeingEdited.getRole());
        role.setEnabled(false);

        TextField employNr = new TextField();
        employNr.setValue(profileBeingEdited.getEmployeeNr());
        employNr.setEnabled(false);

        TextField firstName = new TextField();
        firstName.setValue(profileBeingEdited.getFirstName());
        firstName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField lastName = new TextField();
        lastName.setValue(profileBeingEdited.getLastName());
        lastName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField phone = new TextField();
        phone.setValue(profileBeingEdited.getPhoneNr());
        phone.setValueChangeMode(ValueChangeMode.EAGER);

        TextField email = new TextField();
        email.setValue(profileBeingEdited.getEmail());
        email.setValueChangeMode(ValueChangeMode.EAGER);

        Label infoLabel = new Label();

        NativeButton save = new NativeButton("Save");

        layoutWithBinder.addFormItem(role, "role");
        layoutWithBinder.addFormItem(firstName, "First name");
        layoutWithBinder.addFormItem(lastName, "Last name");
        layoutWithBinder.addFormItem(email, "E-mail");
        layoutWithBinder.addFormItem(employNr, "employnr");
        layoutWithBinder.addFormItem(phone, "Phone");

// Button bar
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save);
        save.getStyle().set("marginRight", "10px");

        SerializablePredicate<String> phoneOrEmailPredicate = value -> !phone
                .getValue().trim().isEmpty()
                || !email.getValue().trim().isEmpty();

// E-mail and phone have specific validators
        Binding<EmployeeProfile, String> emailBinding = binder.forField(email)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .withValidator(new EmailValidator("Incorrect email address"))
                .bind(EmployeeProfile::getEmail, EmployeeProfile::setEmail);

        Binding<EmployeeProfile, String> phoneBinding = binder.forField(phone)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .bind(EmployeeProfile::getPhoneNr, EmployeeProfile::setPhoneNr);

// Trigger cross-field validation when the other field is changed
        email.addValueChangeListener(event -> phoneBinding.validate());
        phone.addValueChangeListener(event -> emailBinding.validate());

// First name and last name are required fields
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);

        binder.forField(firstName)
                .withValidator(new StringLengthValidator(
                        "Please add the first name", 1, null))
                .bind(EmployeeProfile::getFirstName, EmployeeProfile::setFirstName);
        binder.forField(lastName)
                .withValidator(new StringLengthValidator(
                        "Please add the last name", 1, null))
                .bind(EmployeeProfile::getLastName, EmployeeProfile::setLastName);

// Click listeners for the buttons
        save.addClickListener(event -> {
            if (binder.writeBeanIfValid(profileBeingEdited)) {
                infoLabel.setText("Saved bean values: " + profileBeingEdited);
            } else {
                BinderValidationStatus<EmployeeProfile> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("There are errors: " + errorText);
            }
        });

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(layoutWithBinder, save);
        return verticalLayout;
    }

}
