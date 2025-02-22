package org.example.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class AddingEventListener {

    @EventListener
    public void listen(AddingEvent addingEvent) {
        System.out.println(MessageFormat.format("New student {0} has been registered", addingEvent.getStudent()));
    }
}
