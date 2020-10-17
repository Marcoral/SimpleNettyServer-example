package com.github.marcoral.simplenettyserver.example.client;

import com.github.marcoral.simplenettyserver.api.client.DefaultConnectedClient;
import com.github.marcoral.simplenettyserver.example.api.client.SimpleServerUser;
import io.netty.channel.socket.SocketChannel;

public class ExampleServerUser extends DefaultConnectedClient implements SimpleServerUser {
    private LoggedUserData loggedUserData;

    public ExampleServerUser(SocketChannel socketChannel) {
        super(socketChannel);
    }

    public boolean isLoggedIn() {
        return loggedUserData != null;
    }

    public String getName() {
        if(!isLoggedIn())
            throw new IllegalStateException("User is not logged in!");
        return loggedUserData.getName();
    }

    public void logIn(String name) {
        if(isLoggedIn())
            throw new IllegalStateException(String.format("User is already logged in! (%s)", getName()));
        this.loggedUserData = new LoggedUserData(name);
    }

    @Override
    public String toString() {
        String loggedInfo = isLoggedIn()? "logged as " + getName() : "not logged in";
        return getClass().getSimpleName() + String.format(" (%s), IP address: %s", loggedInfo, getSocketChannel().remoteAddress());
    }
}