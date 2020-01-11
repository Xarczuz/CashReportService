package com.CashReportSystem.view;

import com.CashReportSystem.helper.ValidateClientHelper;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.service.TokenService;
import com.CashReportSystem.view.components.MenuBarComponent;
import com.CashReportSystem.view.components.ProfileSettingsFormComponent;
import com.CashReportSystem.view.components.ProfileStatusField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route()
public class ProfileSettingsUi extends VerticalLayout implements BeforeEnterObserver {

    private TokenService tokenService;


    public ProfileSettingsUi(EmployeeProfileRepository employeeProfileRepository, TokenService tokenService, MenuBarComponent menuBarComponent, UserRepository userRepository,
                             CustomerProfileRepository customerProfileRepository) {
        this.tokenService = tokenService;

        EmployeeProfile employeeProfile;
        CustomerProfile customerProfile;


        String username = tokenService.getUsernameFromToken();


        Optional<User> checkUser = userRepository.findByUserName(username);


        System.out.println(checkUser);
        if (checkUser.isPresent()) {
            switch (checkUser.get().getPermission()) {

                case "admin":
                    employeeProfile = employeeProfileRepository.findById(checkUser.get().getId()).get();
                    MenuBar adminMenuBar = menuBarComponent.createMenuBar();
                    Component adminForm = ProfileSettingsFormComponent.createSettingsFormEmployee(employeeProfile, employeeProfileRepository);
                    userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), adminMenuBar, adminForm));
                    break;


                case "employee":
                    employeeProfile = employeeProfileRepository.findById(checkUser.get().getId()).get();
                    MenuBar employeeMenuBar = menuBarComponent.createMenuBar();
                    Component employeeForm = ProfileSettingsFormComponent.createSettingsFormEmployee(employeeProfile, employeeProfileRepository);
                    userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), employeeMenuBar, employeeForm));
                    break;

                case "customer":
                    customerProfile = customerProfileRepository.findById(checkUser.get().getId()).get();
                    MenuBar customerMenuBar = menuBarComponent.createMenuBar();
                    Component customerForm = ProfileSettingsFormComponent.createSettingsFormCustomer(customerProfile, customerProfileRepository);
                    userRepository.findByUserName(username).ifPresent(user1 -> add(ProfileStatusField.createStatusField(user1), customerMenuBar, customerForm));
                    break;


                default:
                    System.out.println("Error");
                    break;

            }
        } else {
            getUI().ifPresent(ui -> ui.navigate("loginui"));
        }
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        ValidateClientHelper.validateCurrentUser(event, tokenService);


    }


}
