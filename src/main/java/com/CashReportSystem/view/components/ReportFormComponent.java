package com.CashReportSystem.view.components;

import com.CashReportSystem.model.CasinoTokens;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ReportFormComponent {

    public static Component createFormReport(Report report, ReportRepository reportRepository) {
        FormLayout layoutWithBinder = new FormLayout();
        Binder<Report> binder = new Binder<>();

        TextField id = new TextField();
        id.setValue(report.getId().toString());
        id.setEnabled(false);

        TextField cashFlow = new TextField();
        if (report.getCashFlow() != null) {
            cashFlow.setValue(report.getCashFlow().toString());
        } else {
            cashFlow.setValue("0");
        }

        TextField customerSign = new TextField();
        //customerSign.setValue(report.getCustomerSign());

        layoutWithBinder.addFormItem(id, "Report nr");
        layoutWithBinder.addFormItem(cashFlow, "Cash Flow");
        layoutWithBinder.addFormItem(customerSign, "Customer Signature");

        Grid<CasinoTokens> grid = new Grid<>(CasinoTokens.class);
        grid.setItems(report.getCasinoTokens());
        layoutWithBinder.addFormItem(grid, "Grid");


/*        TextField employNr = new TextField();

        employNr.setValue(report.getEmployeeNr());
        employNr.setEnabled(false);

        TextField firstName = new TextField();
        firstName.setValue(report.getFirstName());
        firstName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField lastName = new TextField();
        lastName.setValue(report.getLastName());
        lastName.setValueChangeMode(ValueChangeMode.EAGER);

        TextField phone = new TextField();
        phone.setValue(report.getPhoneNr());
        phone.setValueChangeMode(ValueChangeMode.EAGER);

        TextField email = new TextField();
        email.setValue(report.getEmail());
        email.setValueChangeMode(ValueChangeMode.EAGER);

        Label infoLabel = new Label();

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

        Binder.Binding<EmployeeProfile, String> emailBinding = binder.forField(email)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .withValidator(new EmailValidator("Incorrect email address"))
                .bind(EmployeeProfile::getEmail, EmployeeProfile::setEmail);

        Binder.Binding<EmployeeProfile, String> phoneBinding = binder.forField(phone)
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
            if (binder.writeBeanIfValid(report)) {
                infoLabel.setText("Saved bean values: " + report);
                reportRepository.save(report);
            } else {
                BinderValidationStatus<EmployeeProfile> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("There are errors: " + errorText);
            }
        });*/

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(layoutWithBinder);
        return verticalLayout;
    }

}
