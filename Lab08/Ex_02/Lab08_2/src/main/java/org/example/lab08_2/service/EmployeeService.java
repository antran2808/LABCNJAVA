package org.example.lab08_2.service;

import org.example.lab08_2.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployee(long id);
    Employee save(Employee employee);
    void removeById(Long id);
}
