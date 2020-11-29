package dk.via.sep.server.model.eventServerModel;

import dk.via.sep.server.persistence.eventServer.EventDAO;

public class EventServerModelManager implements EventServerModel {

    private EventDAO eventDAO;

    public EventServerModelManager(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }
}
