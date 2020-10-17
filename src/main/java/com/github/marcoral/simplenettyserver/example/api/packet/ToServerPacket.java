package com.github.marcoral.simplenettyserver.example.api.packet;

import com.github.marcoral.simplenettyserver.example.packet.in.LoginRequestPacketDeserializer;
import com.github.marcoral.simplenettyserver.example.packet.in.PingPacketDeserializer;
import com.github.marcoral.simplenettyserver.example.packet.in.SomeIntDataPacketDeserializer;
import com.github.marcoral.simplenettyserver.api.packet.PacketDeserializer;

//Deserializers should be moved out from the API. It is made this way just for simplicity.
public enum ToServerPacket {
    PING(0, new PingPacketDeserializer()),
    SIMPLE_DATA_PACKET(1, new SomeIntDataPacketDeserializer()),
    LOGIN_REQUEST(2, new LoginRequestPacketDeserializer());

    private final int id;
    private final PacketDeserializer deserializer;
    ToServerPacket(int id, PacketDeserializer deserializer) {
        this.id = id;
        this.deserializer = deserializer;
    }

    public int getID() {
        return id;
    }

    public PacketDeserializer getDeserializer() {
        return deserializer;
    }
}
