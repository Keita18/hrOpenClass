package com.example.hropenclass.controllers;

import com.example.hropenclass.model.Employee;
import com.example.hropenclass.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    final
    EmployeeService employeeService;

    public EmployeeController(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Employee>> getEmployee(@PathVariable long id) {
        return ResponseEntity.ok().body(employeeService.getEmployee(id));
    }

    @GetMapping
    ResponseEntity<Iterable<Employee>> getAll() {
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @PostMapping
    ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employee));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if(e.isPresent()) {
            Employee currentEmployee = e.get();

            String firstName = employee.getFirstName();
            if(firstName != null) {
                currentEmployee.setFirstName(firstName);
            }
            String lastName = employee.getLastName();
            if(lastName != null) {
                currentEmployee.setLastName(lastName);;
            }
            String mail = employee.getMail();
            if(mail != null) {
                currentEmployee.setMail(mail);
            }
            String password = employee.getPassword();
            if(password != null) {
                currentEmployee.setPassword(password);;
            }
            employeeService.saveEmployee(currentEmployee);
            return currentEmployee;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
