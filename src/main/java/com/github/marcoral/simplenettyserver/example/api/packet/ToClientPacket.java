package com.github.marcoral.simplenettyserver.example.api.packet;

import com.github.marcoral.simplenettyserver.example.packet.out.LoginErrorResponsePacketSerializer;
import com.github.marcoral.simplenettyserver.example.packet.out.LoginSuccessResponsePacketSerializer;
import com.github.marcoral.simplenettyserver.example.packet.out.PongPacketSerializer;
import com.github.marcoral.simplenettyserver.api.packet.PacketOut;
import com.github.marcoral.simplenettyserver.api.packet.PacketSerializer;

//Serializers should be moved out from the API. It is made this way just for simplicity.
public enum ToClientPacket {
    PONG(0, new PongPacketSerializer()),
    LOGIN_RESPONSE_SUCCESS(1, new LoginSuccessResponsePacketSerializer()),
    LOGIN_RESPONSE_ERROR(2, new LoginErrorResponsePacketSerializer());

    private final int id;
    private final PacketSerializer<?> serializer;
    ToClientPacket(int id, PacketSerializer<?> serializer) {
        this.id = id;
        this.serializer = serializer;
    }

    public int getID() {
        return id;
    }

    public <T extends PacketOut> PacketSerializer<T> getSerializer() {
        return (PacketSerializer<T>) serializer;
    }
}