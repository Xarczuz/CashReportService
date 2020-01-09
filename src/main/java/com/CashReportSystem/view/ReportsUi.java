package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class ReportsUi extends VerticalLayout implements BeforeEnterObserver {

    private ValidateClientHelper validateClientHelper;
    private TokenService tokenService;
    User user;

    public ReportsUi(ReportRepository reportRepository, TokenService tokenService, ValidateClientHelper validateClientHelper) {
        this.validateClientHelper = validateClientHelper;
        this.tokenService = tokenService;

        MenuBar menuBar = MenuBarComponent.createMenuBar();


        // Employees
        List<Report> reportRepositoryAll = reportRepository.findAll();

        Grid<Report> grid = new Grid<>(Report.class);
        grid.setItems(reportRepositoryAll);

        //grid.removeColumnByKey("id");

        add(ProfileStatusField.createStatusField(user), menuBar, grid);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        user = validateClientHelper.validateCurrentUser(event, tokenService);
    }
}
