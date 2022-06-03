package vn.techmaster.demotest.service;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.demotest.model.Employee;
import vn.techmaster.demotest.repository.EmployeeRepo;

public class EmployeeService {
    // @Autowired
    // EmployeeRepo employeeRepo;
    // // tìm theo email + lastName
    // public List<Employee> findByEmailandlastName(String email, String lastName){
    //     return employeeRepo.findAll().stream()
    //     .filter(i -> (i.getEmailAddress().equals(email) && i.getLastName().equals(lastName)))
    //     .collect(Collectors.toList());
    // }
    // // tìm theo fisrtName hoặc lastName
    // public List<Employee> findByFirstOrLastName(String name){
    //     return employeeRepo.findAll().stream()
    //     .filter(i -> (i.getFirstName().equals(name) || i.getLastName().equals(name)))
    //     .collect(Collectors.toList());
    // }

   


    // // tìm theo firstName không phân biệt hoa thường
    // public List<Employee> findByFistNameIgnoreCase(String name){
    //     return employeeRepo.findAll().stream()
    //     .filter(i -> i.getLastName().equalsIgnoreCase(name))
    //     .collect(Collectors.toList());
    // }
}
