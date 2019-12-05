package com.CashReportSystem.repository;

import com.CashReportSystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
