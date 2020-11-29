package dk.via.sep.server.networking.eventServerHandler;

import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;

import java.rmi.Remote;

public interface EventServer extends EventServerCallback, Remote {

}
