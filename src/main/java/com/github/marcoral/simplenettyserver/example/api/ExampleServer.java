package com.github.marcoral.simplenettyserver.example.api;

import com.github.marcoral.simplenettyserver.api.Server;
import com.github.marcoral.simplenettyserver.api.ServerCreator;
import com.github.marcoral.simplenettyserver.api.builder.ServerBuilder;
import com.github.marcoral.simplenettyserver.api.event.EventManager;
import com.github.marcoral.simplenettyserver.api.event.handlers.NewConnectionEventHandler;
import com.github.marcoral.simplenettyserver.api.event.handlers.RemovedConnectionEventHandler;
import com.github.marcoral.simplenettyserver.example.api.packet.ToClientPacket;
import com.github.marcoral.simplenettyserver.example.api.packet.ToServerPacket;
import com.github.marcoral.simplenettyserver.example.client.ExampleServerUser;

import java.util.concurrent.Future;

public class ExampleServer {
    private final Server server;
    private ExampleServer(Server server) {
        this.server = server;
    }

    public static ExampleServer setupOnPort(int port) throws Exception {
        return new ExampleServer(prepareBuilder().launchOnPort(port).get());
    }

    public static ExampleServer setupLocally(int port) throws Exception {
        return new ExampleServer(prepareBuilder().launchLocally(port).get());
    }

    private static ServerBuilder prepareBuilder() {
        final ServerBuilder builder = ServerCreator.runNewServer()
                .clientFactory(ExampleServerUser::new)
                .adjustEventManager(ExampleServer::adjustEventManager);
        addPacketSerializers(builder);
        addPacketDeserializers(builder);
        return builder;
    }

    private static void adjustEventManager(EventManager eventManager) {
        eventManager.addEventHandler((NewConnectionEventHandler) event -> System.out.println("New client: " + event.getClient()));
        eventManager.addEventHandler((RemovedConnectionEventHandler) event -> System.out.println("Disconnected: " + event.getClient()));
    }

    private static void addPacketSerializers(ServerBuilder builder) {
        for(ToClientPacket packet : ToClientPacket.values())
            builder.addPacketSerializer(packet.getID(), packet.getSerializer());
    }

    private static void addPacketDeserializers(ServerBuilder builder) {
        for(ToServerPacket packet : ToServerPacket.values())
            builder.addPacketDeserializer(packet.getID(), packet.getDeserializer());
    }

    public Future<?> shutdown() {
        return server.shutdown();
    }
}