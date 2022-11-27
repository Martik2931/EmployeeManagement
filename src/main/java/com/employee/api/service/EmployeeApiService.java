package com.employee.api.service;

import com.employee.common.EmployeeDTO;
import com.employee.common.EmployeeVM;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeApiService {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeDTO createEmployee(EmployeeVM employeeVM) {
        return employeeService.createEmployee(employeeVM);

    }

    public EmployeeDTO updateEmployee(EmployeeVM employeeVM, int id) {
        return employeeService.updateEmployee(employeeVM, id);
    }

    public EmployeeDTO getEmployee(int id) {
        return employeeService.getEmployee(id);
    }

    public boolean deleteEmployee(int id) {
        return employeeService.deleteEmployee(id);
    }

}
