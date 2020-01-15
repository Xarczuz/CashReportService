package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.CashReportSystem.view.components.ReportFormComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class ReportsUi extends VerticalLayout implements BeforeEnterObserver {

    private TokenService tokenService;
    private final MenuBarComponent menuBarComponent;
    private final UserRepository userRepository;
    private ReportRepository reportRepository;

    public ReportsUi(ReportRepository reportRepository, TokenService tokenService, MenuBarComponent menuBarComponent, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.tokenService = tokenService;
        this.menuBarComponent = menuBarComponent;
        this.userRepository = userRepository;

        MenuBar menuBar = menuBarComponent.createMenuBar();

        // Employees
        List<Report> reportRepositoryAll = reportRepository.findAll();

        Grid<Report> grid = new Grid<>(Report.class);
        grid.setItems(reportRepositoryAll);

        Component buttons = addButtons(grid);

        //grid.removeColumnByKey("id");
        String username = tokenService.getUsernameFromToken();
        userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), menuBar, buttons, grid));

        // The Grid<>(Person.class) sorts the properties and in order to
        // reorder the properties we use the 'setColumns' method.
        grid.setColumns("id", "gameTableName", "location", "employeeSign", "customerSign", "cashFlow", "revenue", "infoField", "status");
        grid.recalculateColumnWidths();

    }

    private Component addButtons(Grid<Report> grid) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button addButton = new Button();
        addButton.setText("Add");
        addButton.addClickListener(buttonClickEvent ->
        {
            remove(grid, horizontalLayout);
            Report report = new Report();
            report = reportRepository.save(report);
            Component reportForm = ReportFormComponent.createFormReport(report, reportRepository);
            add(reportForm);

        });
        Button removeButton = new Button();
        removeButton.setText("Remove");
        removeButton.addClickListener(buttonClickEvent -> {
                    grid.getSelectionModel().getFirstSelectedItem().ifPresent(report -> {
                        reportRepository.deleteById(report.getId());
                        grid.setItems(reportRepository.findAll());
                    });
                }
        );
        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.addClickListener(buttonClickEvent -> {
                }
        );

        horizontalLayout.add(addButton, removeButton, editButton);
        return horizontalLayout;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        ValidateClientHelper.validateCurrentUser(event, tokenService);
    }
}
