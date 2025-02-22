package org.example.event;

import lombok.Getter;
import org.example.Student;
import org.springframework.context.ApplicationEvent;

@Getter
public class AddingEvent extends ApplicationEvent {
    private final Student student;
    public AddingEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }
}
