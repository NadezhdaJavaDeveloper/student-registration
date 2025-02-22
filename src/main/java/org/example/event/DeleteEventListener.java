package org.example.event;

import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class DeleteEventListener {

    @EventListener
    public void listen(DeleteEvent deleteEvent) {
        System.out.println(MessageFormat.format("Student {0} has been removed", deleteEvent.getId()));
    }

}
