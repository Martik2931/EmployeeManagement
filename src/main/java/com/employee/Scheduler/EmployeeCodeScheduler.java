package com.employee.Scheduler;

import com.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class EmployeeCodeScheduler {

    @Autowired
    private EmployeeService employeeService;

    @Scheduled(cron = "${application.employeeCode.scheduler}")
    public void updateEmployeeCodeOnSchedulerTime(){
        log.info("cron wise employee code updating at {}", Instant.now());
        employeeService.updateEmployeesCode();
    }

}
