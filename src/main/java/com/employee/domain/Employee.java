package com.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String department;
    private String salary;
    private String designation;
    private String code;


}
