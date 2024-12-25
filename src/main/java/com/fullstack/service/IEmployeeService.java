package com.fullstack.service;

import com.fullstack.model.Employee;
import jakarta.validation.constraints.Email;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    Optional<Employee>findById(int empId);

    List<Employee>findAll();

    Employee update(Employee employee);

    void deleteById(int empId);

    Employee findByEmpEmailId(@Email(message = "Should add the proper EmailId") String empEmailId);

    Employee findByEmpContactNumber(long empContactNumber);


}
