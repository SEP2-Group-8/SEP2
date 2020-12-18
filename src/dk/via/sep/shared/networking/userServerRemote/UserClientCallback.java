package dk.via.sep.shared.networking.userServerRemote;

import dk.via.sep.shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The client callback interface that is necessary for the server to call methods back on the client.
 * <b>Every method here needs to throw a RemoteException, as per RMI's specification</b>.
 *
 * @author Bogdan-Florin Cirstoiu
 */
public interface UserClientCallback extends Remote {

    /**
     * Method used to send the updated user data back to the client that updated it.
     * This method is called only if a user edited it's profile credentials, and then it will provide the updated credentials as a parameter.
     *
     * @param newUser the updated user data.
     * @throws RemoteException if the connection cannot be established.
     */
    void updateProfile(User newUser) throws RemoteException;
}
