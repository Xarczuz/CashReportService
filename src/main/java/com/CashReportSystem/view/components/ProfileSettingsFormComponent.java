package com.CashReportSystem.view.components;

import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.repository.EmployeeProfileRepository;
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
    public static Component createSettingsFormEmployee(EmployeeProfile employeeProfile, EmployeeProfileRepository employeeProfileRepository) {
        FormLayout layoutWithBinder = new FormLayout();
        Binder<EmployeeProfile> binder = new Binder<>();

        TextField role = new TextField();
        role.setValue(employeeProfile.getRole());
        role.setEnabled(false);

        TextField employNr = new TextField();
        employNr.setValue(employeeProfile.getEmployeeNr());
        employNr.setEnabled(false);

        TextField firstName = new TextField();
        firstName.setValue(employeeProfile.getFirstName());
        firstName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField lastName = new TextField();
        lastName.setValue(employeeProfile.getLastName());
        lastName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField phone = new TextField();
        phone.setValue(employeeProfile.getPhoneNr());
        phone.setValueChangeMode(ValueChangeMode.EAGER);

        TextField email = new TextField();
        email.setValue(employeeProfile.getEmail());
        email.setValueChangeMode(ValueChangeMode.EAGER);

        Label infoLabel = new Label();
        infoLabel.setText("");

        NativeButton save = new NativeButton("Save");

        layoutWithBinder.addFormItem(role, "role");
        layoutWithBinder.addFormItem(firstName, "First name");
        layoutWithBinder.addFormItem(lastName, "Last name");
        layoutWithBinder.addFormItem(email, "E-mail");
        layoutWithBinder.addFormItem(employNr, "employnr");
        layoutWithBinder.addFormItem(phone, "Phone");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save);
        save.getStyle().set("marginRight", "10px");

        SerializablePredicate<String> phoneOrEmailPredicate = value -> !phone
                .getValue().trim().isEmpty()
                || !email.getValue().trim().isEmpty();

        Binding<EmployeeProfile, String> emailBinding = binder.forField(email)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .withValidator(new EmailValidator("Incorrect email address"))
                .bind(EmployeeProfile::getEmail, EmployeeProfile::setEmail);

        Binding<EmployeeProfile, String> phoneBinding = binder.forField(phone)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .bind(EmployeeProfile::getPhoneNr, EmployeeProfile::setPhoneNr);

        email.addValueChangeListener(event -> phoneBinding.validate());
        phone.addValueChangeListener(event -> emailBinding.validate());

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

        save.addClickListener(event -> {
            if (binder.writeBeanIfValid(employeeProfile)) {
                employeeProfileRepository.save(employeeProfile);
                infoLabel.setText("Saved");
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
//TODO BINDER
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(layoutWithBinder, infoLabel, save);
        return verticalLayout;
    }

    public static Component createSettingsFormCustomer(CustomerProfile customerProfile, CustomerProfileRepository customerProfileRepository) {
        FormLayout layoutWithBinder = new FormLayout();
        Binder<CustomerProfile> binder = new Binder<>();
//TODO BINDER
        TextField adress = new TextField();
        adress.setValue(customerProfile.getAddress());
        adress.setValueChangeMode(ValueChangeMode.EAGER);

        TextField company = new TextField();
        company.setValue(customerProfile.getCompanyName());
        company.setValueChangeMode(ValueChangeMode.EAGER);

        TextField firstName = new TextField();
        firstName.setValue(customerProfile.getFirstName());
        firstName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField lastName = new TextField();
        lastName.setValue(customerProfile.getLastName());
        lastName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField phone = new TextField();
        phone.setValue(customerProfile.getPhoneNr());
        phone.setValueChangeMode(ValueChangeMode.EAGER);

        TextField email = new TextField();
        email.setValue(customerProfile.getEmail());
        email.setValueChangeMode(ValueChangeMode.EAGER);

        TextField orgNr = new TextField();
        orgNr.setValue(customerProfile.getOrgNr());
        orgNr.setValueChangeMode(ValueChangeMode.EAGER);

        Label infoLabel = new Label();
        infoLabel.setText("");

        NativeButton save = new NativeButton("Save");

        layoutWithBinder.addFormItem(adress, "adress");
        layoutWithBinder.addFormItem(firstName, "First name");
        layoutWithBinder.addFormItem(lastName, "Last name");
        layoutWithBinder.addFormItem(email, "E-mail");
        layoutWithBinder.addFormItem(company, "company");
        layoutWithBinder.addFormItem(phone, "Phone");
        layoutWithBinder.addFormItem(orgNr, "orgnr");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save);
        save.getStyle().set("marginRight", "10px");

        SerializablePredicate<String> phoneOrEmailPredicate = value -> !phone
                .getValue().trim().isEmpty()
                || !email.getValue().trim().isEmpty();

        Binding<CustomerProfile, String> emailBinding = binder.forField(email)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .withValidator(new EmailValidator("Incorrect email address"))
                .bind(CustomerProfile::getEmail, CustomerProfile::setEmail);

        Binding<CustomerProfile, String> phoneBinding = binder.forField(phone)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .bind(CustomerProfile::getPhoneNr, CustomerProfile::setPhoneNr);

        email.addValueChangeListener(event -> phoneBinding.validate());
        phone.addValueChangeListener(event -> emailBinding.validate());

        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);

        binder.forField(firstName)
                .withValidator(new StringLengthValidator(
                        "Please add the first name", 1, null))
                .bind(CustomerProfile::getFirstName, CustomerProfile::setFirstName);
        binder.forField(lastName)
                .withValidator(new StringLengthValidator(
                        "Please add the last name", 1, null))
                .bind(CustomerProfile::getLastName, CustomerProfile::setLastName);

        save.addClickListener(event -> {
            if (binder.writeBeanIfValid(customerProfile)) {
                customerProfileRepository.save(customerProfile);
                infoLabel.setText("Saved");
            } else {
                BinderValidationStatus<CustomerProfile> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("There are errors: " + errorText);
            }
        });

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(layoutWithBinder, infoLabel, save);
        return verticalLayout;
    }

}
