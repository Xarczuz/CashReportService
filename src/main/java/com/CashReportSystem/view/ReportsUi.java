package com.CashReportSystem.view;

import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class ReportsUi extends VerticalLayout {
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public ReportsUi(ReportRepository reportRepository) {
        MenuBar menuBar = MenuBarComponent.createMenuBar();

        TextField statusField = new TextField();
        statusField.setValue("Admin");
        statusField.setLabel("Profile Status");
        statusField.setEnabled(false);

        // Employees
        List<Report> reportRepositoryAll = reportRepository.findAll();

        Grid<Report> grid = new Grid<>(Report.class);
        grid.setItems(reportRepositoryAll);

        //grid.removeColumnByKey("id");

        add(statusField,menuBar, grid);
        // The Grid<>(Person.class) sorts the properties and in order to
        // reorder the properties we use the 'setColumns' method.
        //grid.setColumns("id","companyName","orgNr","firstName", "lastName", "address","email", "phoneNr");
    }
}
