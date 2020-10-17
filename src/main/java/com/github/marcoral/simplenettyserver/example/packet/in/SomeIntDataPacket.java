package com.github.marcoral.simplenettyserver.example.packet.in;

import com.github.marcoral.simplenettyserver.api.client.ConnectedClient;
import com.github.marcoral.simplenettyserver.api.exception.InvalidPacketException;
import com.github.marcoral.simplenettyserver.api.packet.PacketIn;
import com.github.marcoral.simplenettyserver.example.api.client.SimpleServerLoggedUser;
import io.netty.channel.ChannelHandlerContext;

public class SomeIntDataPacket implements PacketIn {
    private final int receivedNumber;
    public SomeIntDataPacket(int receivedNumber) {
        this.receivedNumber = receivedNumber;
    }

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, ConnectedClient connectedClient) throws InvalidPacketException {
        if(!(connectedClient instanceof SimpleServerLoggedUser))
            throw new InvalidPacketException("You must be logged in!");
        System.out.println(String.format("Received %d from %s.", receivedNumber, connectedClient));
    }
}