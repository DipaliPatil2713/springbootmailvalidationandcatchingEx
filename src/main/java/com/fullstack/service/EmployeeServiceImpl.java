package com.fullstack.service;

import com.fullstack.model.Employee;
import com.fullstack.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements  IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId,empPassword);

        if (employee!=null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
            flag =true;
        }
        return flag;
    }

    @Override
    @Cacheable(value = "empId")
    public Optional<Employee> findById(int empId) {
        log.info("#########Trying to fetch data from database ##########");
        return employeeRepository.findById(empId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int empId) {
    employeeRepository.deleteById(empId);
    }

    @Override
    public Employee findByEmpEmailId(String empEmailId) {
        return employeeRepository.findByEmpEmailId(empEmailId);
    }

    @Override
    public Employee findByEmpContactNumber(long empContactNumber) {
        return employeeRepository.findByEmpContactNumber(empContactNumber);
    }
}
