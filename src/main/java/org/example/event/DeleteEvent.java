package org.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteEvent extends ApplicationEvent {

    private final Integer id;

    public DeleteEvent(Object source, Integer id) {
        super(source);
        this.id = id;
    }
}
