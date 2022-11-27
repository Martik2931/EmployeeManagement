package com.employee.service.mapper;

import com.employee.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("emp_first_name"));
        employee.setLastName(rs.getString("emp_last_name"));
        employee.setMobileNumber(rs.getString("emp_mobile_number"));
        employee.setEmail(rs.getString("emp_email"));
        employee.setDepartment(rs.getString("emp_department"));
        employee.setSalary(rs.getString("emp_salary"));
        employee.setDesignation(rs.getString("emp_designation"));
        employee.setCode(rs.getString("emp_code"));
        return employee;
    }
}
