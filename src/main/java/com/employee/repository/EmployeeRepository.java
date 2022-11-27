package com.employee.repository;

import com.employee.domain.Employee;
import com.employee.service.mapper.EmployeeRowMapper;
import com.employee.web.rest.exception.EmployeeException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Async
    public int createEmployee(@NonNull Employee employee) {
        try {
            String insertEmployee = "INSERT INTO db_employee (emp_first_name, emp_last_name, emp_mobile_number, emp_email, emp_department, emp_salary, emp_designation, emp_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertEmployee, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getMobileNumber());
                ps.setString(4, employee.getEmail());
                ps.setString(5, employee.getDepartment());
                ps.setString(6, employee.getSalary());
                ps.setString(7, employee.getDesignation());
                ps.setString(8, employee.getCode());
                return ps;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKey()).intValue();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new EmployeeException(ex.getMessage());
        }
    }

    @Async
    public int updateEmployee(@NonNull Employee employee, @NonNull int id) {
        try {
            return jdbcTemplate.update("UPDATE db_employee SET emp_first_name = ?, emp_last_name = ?, emp_mobile_number = ?, emp_email = ?, emp_department = ?, emp_salary = ?, emp_designation = ?, WHERE id = ?",
                    employee.getFirstName(), employee.getLastName(), employee.getMobileNumber(), employee.getEmail(), employee.getDepartment(), employee.getSalary(), employee.getDesignation(), id);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new EmployeeException(ex.getMessage());
        }
    }

    @Async
    public Employee getEmployee(@NonNull int id) {
        try {
            String query = "SELECT * FROM db_employee WHERE id = ?";
            return jdbcTemplate.queryForObject(
                    query, new EmployeeRowMapper(), id);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new EmployeeException("Employee is not found for id: " + id);
        }
    }

    @Async
    public boolean deleteEmployee(@NonNull int id) {
        try {
            String query = "delete FROM db_employee WHERE id = ?";
            int isDeleted = jdbcTemplate.update(query, id);
            return isDeleted == 1;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new EmployeeException(ex.getMessage());
        }
    }

    @Async
    public Optional<List<Map<String, Object>>> getAllEmployeeId() {
        try {
            String query = "SELECT id FROM db_employee";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(query);
            return Optional.of(maps);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return Optional.empty();
    }

    @Async
    public int[] employeesCodeBatchUpdate(Map<Object, String> employeeMAP) {
        return jdbcTemplate.batchUpdate(
                "update db_employee set emp_code = ? where id = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {

                        Object employeeKey = getEmployeeKey(i);
                        int employeeId = getEmployeeId(employeeKey);

                        ps.setInt(2, employeeId);
                        ps.setString(1, employeeMAP.get(employeeKey));

                    }

                    private Object getEmployeeKey(int i) {
                        return employeeMAP.keySet().toArray()[i];
                    }

                    private int getEmployeeId(Object key) {
                        return Integer.parseInt(key.toString());
                    }

                    public int getBatchSize() {
                        return employeeMAP.size();
                    }
                });
    }


}
