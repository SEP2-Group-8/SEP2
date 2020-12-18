package dk.via.sep.server.networking.eventServerHandler;

import dk.via.sep.shared.networking.eventServerRemote.EventServerCallback;

import java.rmi.Remote;

/**
 * Event server interface that can be used for methods the client should not access.
 * Currently empty as there is no need for any.
 * If you wish to add <b>remote</b> methods, do so in the EventServerCallback interface.
 */
public interface EventServer extends EventServerCallback, Remote {

}
