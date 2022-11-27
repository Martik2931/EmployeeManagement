package com.employee.web.rest;

import com.employee.api.service.EmployeeApiService;
import com.employee.common.EmployeeDTO;
import com.employee.common.EmployeeVM;
import com.employee.service.EmployeeService;
import com.employee.web.rest.response.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeResource {

    @Autowired
    private EmployeeApiService employeeApiService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<EmployeeDTO>> createEmployee(@RequestBody @Valid EmployeeVM employeeVM) {
        log.info("REST call to create employee");
        EmployeeDTO employeeDTO = employeeApiService.createEmployee(employeeVM);
        APIResponse<EmployeeDTO> apiResponse = new APIResponse();
        apiResponse.setData(employeeDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<EmployeeDTO>> updateEmployee(@RequestBody EmployeeVM employeeVM, @PathVariable("id") int id) {
        log.info("REST call to update employee");
        EmployeeDTO employeeDTO = employeeApiService.updateEmployee(employeeVM, id);
        APIResponse<EmployeeDTO> apiResponse = new APIResponse();
        apiResponse.setData(employeeDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponse<EmployeeDTO>> getEmployee(@PathVariable("id") int id) {
        log.info("REST call to get employee");
        EmployeeDTO employeeDTO = employeeApiService.getEmployee(id);
        APIResponse<EmployeeDTO> apiResponse = new APIResponse();
        apiResponse.setData(employeeDTO);
        return ResponseEntity.ok(apiResponse);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteEmployee(@PathVariable("id") int id) {
        log.info("REST call to remove employee");
        boolean isDeleteEmployee = employeeApiService.deleteEmployee(id);
        APIResponse<Boolean> apiResponse = new APIResponse();
        apiResponse.setData(isDeleteEmployee);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get-all")
    public /*ResponseEntity<APIResponse<List<InventoryDTO>>>*/void  fetchAllInventory() {
        log.info("REST call to fetch all Inventory");
        employeeService.updateEmployeesCode();
       /* APIResponse<List<InventoryDTO>> apiResponse = new APIResponse();
        apiResponse.setData(inventoryDTOS);
        return ResponseEntity.ok(apiResponse);*/
    }


}
