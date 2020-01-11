package com.CashReportSystem.repository;

import com.CashReportSystem.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findByUserId(long userId);
}
