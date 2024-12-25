package com.fullstack.controller;


import com.fullstack.exception.RecordExistException;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.Employee;
import com.fullstack.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/signup")
    ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {


        if (employeeService.findByEmpEmailId(employee.getEmpEmailId()) != null) {
            throw new RecordExistException("Employee Email Already exist in DB, Please try with another email id");
        }

        if (employeeService.findByEmpContactNumber(employee.getEmpContactNumber()) != null) {
            throw new RecordExistException("Employee Contact Number Already exist in DB, Please try with another email id");
        }


        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("signin")
    ResponseEntity<Boolean> signIn(@RequestParam String empEmailId, @RequestParam String empPassword) {
        return new ResponseEntity<>(employeeService.signIn(empEmailId, empPassword), HttpStatus.OK);
    }

    @GetMapping("/findbyId")
    ResponseEntity<Optional<Employee>> findById(@RequestParam int empId) {
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    ResponseEntity<Employee> update(@PathVariable int empId, @Valid @RequestBody Employee employee) {

        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee does not exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());

        return new ResponseEntity<>(employeeService.update(employee1), HttpStatus.CREATED);

    }

    @DeleteMapping("/deletebyid")
    ResponseEntity<String> deleteById(@PathVariable int empId) {
        employeeService.deleteById(empId);
        return new ResponseEntity<>("Data Deletd Successsfully", HttpStatus.OK);
    }
}
