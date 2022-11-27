package com.employee.service;

import com.employee.common.EmployeeDTO;
import com.employee.common.EmployeeVM;
import com.employee.domain.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeVM employeeVM) {
        Employee employee = employeeMapper.employeeVmTOEmployee(employeeVM);
        employee.setCode(getEmployeesCode());
        int employeeId = employeeRepository.createEmployee(employee);
        return employeeMapper.employeeTOEmployeeDTO(employeeRepository.getEmployee(employeeId));
    }

    public EmployeeDTO updateEmployee(EmployeeVM employeeVM, int id) {
        Employee employee = employeeMapper.employeeVmTOEmployee(employeeVM);
        int employeeId = employeeRepository.updateEmployee(employee, id);
        return employeeMapper.employeeTOEmployeeDTO(employeeRepository.getEmployee(employeeId));
    }

    public EmployeeDTO getEmployee(int id) {
        return employeeMapper.employeeTOEmployeeDTO(employeeRepository.getEmployee(id));
    }

    public boolean deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }
    public void updateEmployeesCode() {
        Optional<List<Map<String, Object>>> employeesIdMapOptional = employeeRepository.getAllEmployeeId();
        if (employeesIdMapOptional.isEmpty()) {
            return;
        }
        List<Object> employeeIdList = EmployeeMapper.employeesMapToEmployeeList(employeesIdMapOptional.get());
        int[] employeesId = employeeRepository.employeesCodeBatchUpdate(getEmployeeCodeMap(employeeIdList));
        log.info("employees updated code count {}", employeesId.length);
    }

    public Map<Object, String> getEmployeeCodeMap(List<Object> employeeIdList) {
        Map<Object, String> employeeMAP = new HashMap<>();
        employeeIdList.forEach(
                employeeId ->  {
                    employeeMAP.put(employeeId, getEmployeesCode());
                }
        );
        return employeeMAP;

    }

    public String getEmployeesCode() {
        String empCode = "emp";
        long tempStamp = Timestamp.from(Instant.now()).getTime();
        return empCode + "_" + tempStamp;
    }


}
