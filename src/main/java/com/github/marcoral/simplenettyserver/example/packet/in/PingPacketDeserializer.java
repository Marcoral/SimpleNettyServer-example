package com.github.marcoral.simplenettyserver.example.packet.in;

import com.github.marcoral.nettyencodingutil.BytesDecoder;
import com.github.marcoral.simplenettyserver.api.client.ConnectedClient;
import com.github.marcoral.simplenettyserver.api.packet.PacketDeserializer;
import com.github.marcoral.simplenettyserver.api.packet.PacketIn;

public class PingPacketDeserializer implements PacketDeserializer {
    @Override
    public PacketIn deserialize(BytesDecoder bytesDecoder, ConnectedClient client) {
        return new PingPacket();
    }
}