package com.github.marcoral.simplenettyserver.example.packet.in;

import com.github.marcoral.simplenettyserver.api.client.ConnectedClient;
import com.github.marcoral.simplenettyserver.api.packet.PacketIn;
import com.github.marcoral.simplenettyserver.example.client.ExampleServerUser;
import com.github.marcoral.simplenettyserver.example.packet.out.LoginErrorResponsePacketImpl;
import com.github.marcoral.simplenettyserver.example.packet.out.LoginSuccessResponsePacketImpl;
import io.netty.channel.ChannelHandlerContext;

public class LoginRequestPacket implements PacketIn {
    private final String requestedName;
    public LoginRequestPacket(CharSequence requestedName) {
        this.requestedName = requestedName.toString();
    }

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, ConnectedClient connectedClient) {
        ExampleServerUser user = (ExampleServerUser) connectedClient;

        if (user.isLoggedIn()) {
            connectedClient.sendPacket(new LoginErrorResponsePacketImpl("You are already logged in!"));
            return;
        }

        if(requestedName.length() < 3) {
            connectedClient.sendPacket(new LoginErrorResponsePacketImpl("Requested login is too short! It must contains at least 3 characters."));
            return;
        }

        if(requestedName.length() > 16) {
            connectedClient.sendPacket(new LoginErrorResponsePacketImpl("Requested login is too long! It must contains maximum 16 characters."));
            return;
        }

        //TODO: Check if name is not occupied yet
        user.logIn(requestedName);
        connectedClient.sendPacket(new LoginSuccessResponsePacketImpl());
    }
}