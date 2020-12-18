package dk.via.sep.shared.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock implements Runnable, Subject {
    private final DateTimeFormatter dateTimeFormatter;
    private LocalDateTime localDateTime;
    private final PropertyChangeSupport support;

    public Clock() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while (true) {
            localDateTime = LocalDateTime.now();
            support.firePropertyChange("Clock", null, dateTimeFormatter.format(localDateTime));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
