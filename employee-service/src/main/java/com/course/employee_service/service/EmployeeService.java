package com.course.employee_service.service;

import com.course.employee_service.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public EmployeeService() {
        employees.add(new Employee(
                idCounter.getAndIncrement(),
                "Rahul",
                "Sharma",
                "rahul.sharma@example.com",
                1L
        ));
        employees.add(new Employee(
                idCounter.getAndIncrement(),
                "Priya",
                "Mehta",
                "priya.mehta@example.com",
                1L
        ));
        employees.add(new Employee(
                idCounter.getAndIncrement(),
                "Amit",
                "Verma",
                "amit.verma@example.com",
                2L
        ));
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public Employee createEmployee(Employee employee) {
        employee.setId(idCounter.getAndIncrement());
        employees.add(employee);
        return employee;
    }

    public Optional<Employee> updateEmployee(Long id,
                                             Employee updated) {
        return getEmployeeById(id).map(existing -> {
            existing.setFirstName(updated.getFirstName());
            existing.setLastName(updated.getLastName());
            existing.setEmail(updated.getEmail());
            existing.setDepartmentId(updated.getDepartmentId());
            return existing;
        });
    }

    public boolean deleteEmployee(Long id) {
        return employees.removeIf(e -> e.getId().equals(id));
    }
}