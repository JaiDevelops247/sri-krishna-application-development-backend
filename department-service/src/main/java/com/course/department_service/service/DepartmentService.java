package com.course.department_service.service;

import com.course.department_service.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DepartmentService {

    private final List<Department> departments = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public DepartmentService() {
        departments.add(new Department(
                idCounter.getAndIncrement(),
                "Computer Science",
                "Covers software engineering, algorithms, and data structures"
        ));
        departments.add(new Department(
                idCounter.getAndIncrement(),
                "Electronics",
                "Covers circuits, embedded systems, and signal processing"
        ));
        departments.add(new Department(
                idCounter.getAndIncrement(),
                "Mechanical Engineering",
                "Covers thermodynamics, manufacturing, and machine design"
        ));
    }

    public List<Department> getAllDepartments() {
        return departments;
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departments.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public Department createDepartment(Department department) {
        department.setId(idCounter.getAndIncrement());
        departments.add(department);
        return department;
    }

    public Optional<Department> updateDepartment(Long id,
                                                 Department updated) {
        return getDepartmentById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            return existing;
        });
    }

    public boolean deleteDepartment(Long id) {
        return departments.removeIf(d -> d.getId().equals(id));
    }
}