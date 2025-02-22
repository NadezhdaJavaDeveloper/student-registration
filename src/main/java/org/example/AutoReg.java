package org.example;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AutoReg {
    private final Map<Integer, Student> students = new HashMap<>();
    public AutoReg() {
        registerBasicPoolOfStudents();
    }
    private void registerBasicPoolOfStudents() {
        Student student1 = Student.builder().firstName("Ivan").lastName("Ivanov").age(17).id(students.size() + 1).build();
        students.put(student1.getId(), student1);
        Student student2 = Student.builder().firstName("Peter").lastName("Petrov").age(18).id(students.size() + 1).build();
        students.put(student2.getId(), student2);
    }
}
