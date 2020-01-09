package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.repository.UserRepository;
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

    private TokenService tokenService;

    public ReportsUi(ReportRepository reportRepository, TokenService tokenService, MenuBarComponent menuBarComponent, UserRepository userRepository) {
        this.tokenService = tokenService;

        MenuBar menuBar = menuBarComponent.createMenuBar();


        // Employees
        List<Report> reportRepositoryAll = reportRepository.findAll();

        Grid<Report> grid = new Grid<>(Report.class);
        grid.setItems(reportRepositoryAll);

        //grid.removeColumnByKey("id");
        String username = tokenService.getUsernameFromToken();
        userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), menuBar, grid));

        // The Grid<>(Person.class) sorts the properties and in order to
        // reorder the properties we use the 'setColumns' method.
        //grid.setColumns("id","companyName","orgNr","firstName", "lastName", "address","email", "phoneNr");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        ValidateClientHelper.validateCurrentUser(event, tokenService);
    }
}
