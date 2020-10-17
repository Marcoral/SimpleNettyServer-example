package com.github.marcoral.simplenettyserver.example.packet.in;

import com.github.marcoral.simplenettyserver.example.packet.out.PongPacketImpl;
import com.github.marcoral.simplenettyserver.api.client.ConnectedClient;
import com.github.marcoral.simplenettyserver.api.packet.PacketIn;
import io.netty.channel.ChannelHandlerContext;

public class PingPacket implements PacketIn {
    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, ConnectedClient connectedClient) {
        connectedClient.sendPacket(new PongPacketImpl());
    }
}