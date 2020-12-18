package dk.via.sep.server.networking.userServerHandler;

import dk.via.sep.shared.networking.userServerRemote.UserServerCallback;

import java.rmi.Remote;

/**
 * User server interface, can be used for private methods that the client will not have access to.
 */
public interface UserServer extends UserServerCallback, Remote {
    //this interface is empty because the server only has remote methods currently.
    //this will contain methods that the client does not have access to.
}
