package com.course.student_api.service;

import com.course.student_api.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public StudentService() {
        students.add(new Student(
                idCounter.getAndIncrement(),
                "Rahul Sharma",
                "rahul@example.com",
                "Computer Science",
                2
        ));
        students.add(new Student(
                idCounter.getAndIncrement(),
                "Priya Mehta",
                "priya@example.com",
                "Electronics",
                3
        ));
        students.add(new Student(
                idCounter.getAndIncrement(),
                "Amit Verma",
                "amit@example.com",
                "Mechanical",
                1
        ));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Student createStudent(Student student) {
        student.setId(idCounter.getAndIncrement());
        students.add(student);
        return student;
    }

    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        return getStudentById(id).map(existing -> {
            existing.setName(updatedStudent.getName());
            existing.setEmail(updatedStudent.getEmail());
            existing.setBranch(updatedStudent.getBranch());
            existing.setYear(updatedStudent.getYear());
            return existing;
        });
    }

    public boolean deleteStudent(Long id) {
        return students.removeIf(s -> s.getId().equals(id));
    }
}