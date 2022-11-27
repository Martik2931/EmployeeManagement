package com.employee.service.mapper;

import com.employee.common.EmployeeDTO;
import com.employee.common.EmployeeVM;
import com.employee.domain.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeMapper {

    public Employee employeeVmTOEmployee(EmployeeVM employeeVM) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeVM, employee);
        return employee;
    }

    public EmployeeDTO employeeTOEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public static List<Object> employeesMapToEmployeeList(List<Map<String, Object>> maps) {

        List<Object> employees = new ArrayList<>();
        maps.forEach(map -> {
            ArrayList<Object> employeesList = new ArrayList<>(map.values());
            employees.addAll(employeesList);
        });
        return employees;
    }

}
