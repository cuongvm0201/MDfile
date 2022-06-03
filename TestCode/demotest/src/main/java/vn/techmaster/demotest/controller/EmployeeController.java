package vn.techmaster.demotest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demotest.model.Employee;
import vn.techmaster.demotest.repository.EmployeeRepo;
import vn.techmaster.demotest.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


// @RestController
// @RequestMapping("/api")
public class EmployeeController {
    // @Autowired EmployeeRepo employeeRepo;
    // @Autowired EmployeeService employeeService;
    // @GetMapping("employee")
    // public ResponseEntity<List<Employee>> getAll() {
    //     List<Employee> listAll = employeeRepo.findAll();
    //     return ResponseEntity.ok().body(listAll);
    // }

    // @PostMapping("/serch-by-email-lastname")
    // public ResponseEntity<List<Employee>> findByEmailandLastName(@RequestBody Employee employee){
    //     List<Employee> employees = employeeService.findByEmailandlastName(employee.getEmailAddress(), employee.getLastName());
    //     return ResponseEntity.ok().body(employees);
    // }

    // @GetMapping("/{name}")
    // public ResponseEntity<List<Employee>> findByFirstOrLastName(@PathVariable String name){
    //     List<Employee> employee = employeeService.findByFirstOrLastName(name);
    //     return ResponseEntity.ok().body(employee);
    // }
    
}
