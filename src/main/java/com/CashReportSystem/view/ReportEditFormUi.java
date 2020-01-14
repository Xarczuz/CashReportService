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
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

public class ReportEditFormUi extends VerticalLayout implements BeforeEnterObserver {
    private TokenService tokenService;

    public ReportEditFormUi(ReportRepository reportRepository, TokenService tokenService, MenuBarComponent menuBarComponent, UserRepository userRepository) {
        this.tokenService = tokenService;

        MenuBar menuBar = menuBarComponent.createMenuBar();
        String username = tokenService.getUsernameFromToken();
        Report report = new Report();
        Component reportForm = ReportFormComponent.createFormReport(report, reportRepository);
        userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), menuBar, reportForm));

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        ValidateClientHelper.validateCurrentUser(beforeEnterEvent, tokenService);

    }
}

