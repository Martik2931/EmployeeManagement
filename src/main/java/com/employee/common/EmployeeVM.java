package com.employee.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeVM {

    @NotNull(message = "First Name is Required: Empty first name")
    @NotBlank(message = "First Name is Required: first name is NULL")
    private String firstName;

    @NotNull(message = "Last Name is Required: Empty name")
    @NotBlank(message = "Last Name is Required: Name is NULL")
    private String lastName;

    @NotBlank(message = "Invalid Phone number: Empty Mobile Number")
    @NotNull(message = "Invalid Phone number: Mobile Number is NULL")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String mobileNumber;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid Phone number: Empty department")
    @NotNull(message = "Invalid Phone number: department is NULL")
    private String department;

    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Size(min = 3, max = 10, message = "Invalid salary: Must be of 3 - 10 characters")
    private String salary;

    @NotBlank(message = "Invalid designation: designation")
    @NotNull(message = "Invalid designation: designation")
    private String designation;

}
