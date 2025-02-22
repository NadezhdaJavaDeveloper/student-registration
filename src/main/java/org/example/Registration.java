package org.example;

import lombok.RequiredArgsConstructor;
import org.example.event.AddingEvent;
import org.example.event.DeleteEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

@ShellComponent
@RequiredArgsConstructor
public class Registration {
    private final Map<Integer, Student> students;
    private final ApplicationEventPublisher publisher;

    private final AtomicInteger currentId = new AtomicInteger(1);

    @Value("${app.event-queue.enabled}")
    private boolean isPreregistrationAvailable;

    @EventListener(ApplicationStartedEvent.class)
    public void registerBasicPoolOfStudents() {
        if(isPreregistrationAvailable) {
            students.putAll(new AutoReg().getStudents());
            currentId.getAndAdd(students.size());
            System.out.println("2 студента попали в список по предварительной записи");

        }
    }
    @ShellMethod(key="as")
    public void addStudent(String firstName, String lastName, int age) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        int studentId = currentId.getAndIncrement();
        student.setId(studentId);

        System.out.println("добавился студент с id " + studentId);
        publisher.publishEvent(new AddingEvent(this, student));
        students.put(student.getId(), student);
    }
    @ShellMethod(key="gas")
    public String getAllStudent() {
        if(students.size()==0) {
            return "list of students is empty";
        }
        StringJoiner joiner = new StringJoiner("\n");
        for(Map.Entry<Integer, Student> entry : students.entrySet()) {
            joiner.add(entry.getValue().toString());
        }
        return joiner.toString();
    }
    @ShellMethod(key="dsbi")
    public void deleteStudentById(int id) {
        if(students.get(id) != null) {
            publisher.publishEvent(new DeleteEvent(this, id));
            students.remove(id);

        } else {
            System.out.println("Student with the specified id is not registered");
        }
    }
    @ShellMethod(key="das")
    public String deleteAllStudent() {
        students.clear();
        return "Student's list is empty";
    }



}
