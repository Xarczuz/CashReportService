package com.CashReportSystem.repository;

import com.CashReportSystem.model.TimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeStampRepository extends JpaRepository<TimeStamp, Long> {
}
