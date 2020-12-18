package dk.via.sep.shared.utils;

import java.beans.PropertyChangeListener;

/**
 * Interface used for the observer pattern.
 */
public interface Subject {

    /**
     * Method used to add a listener to a class.
     *
     * @param eventName name of the event to listen for
     * @param listener  the listener of the event
     */
    void addListener(String eventName, PropertyChangeListener listener);

    /**
     * Method used to remove a listener from a class.
     *
     * @param eventName name of the event to listen for
     * @param listener  the listener of the event
     */
    void removeListener(String eventName, PropertyChangeListener listener);
}
