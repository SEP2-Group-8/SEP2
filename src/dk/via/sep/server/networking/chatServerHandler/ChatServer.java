package dk.via.sep.server.networking.chatServerHandler;

import dk.via.sep.shared.networking.chatServerRemote.ChatServerCallback;

import java.rmi.Remote;

public interface ChatServer extends ChatServerCallback, Remote {
}
