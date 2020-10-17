package com.github.marcoral.simplenettyserver.example.api.packet;

import com.github.marcoral.simplenettyserver.api.packet.PacketOut;

public interface LoginErrorResponsePacket extends PacketOut {
    String getCause();
}