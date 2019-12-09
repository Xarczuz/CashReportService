package com.CashReportSystem.repository;

import com.CashReportSystem.model.Report;
import com.CashReportSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
