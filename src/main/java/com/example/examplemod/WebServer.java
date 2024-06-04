package com.example.examplemod;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import net.minecraft.server.MinecraftServer;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import java.util.List;
import java.util.stream.Collectors;

public class WebServer {

    private final Undertow server;
    private MinecraftServer mcServer;
    private static final Logger LOGGER = LogUtils.getLogger();

    // Create a handler for the hello world endpoint
    HttpHandler helloWorldHandler = exchange -> {
        exchange.getResponseSender().send("Hello, World!");
    };

    // Create a handler to send current player list
    HttpHandler playerListHandler = exchange -> {
        exchange.getResponseSender().send(getPlayers().toString());
    };

    public WebServer(int port) {
        this.server = Undertow.builder()
                .addHttpListener(port, "localhost")
//                .setHandler(Handlers.path().addPrefixPath("/players", playerListHandler))
                .setHandler(Handlers.path().addPrefixPath("/hello", helloWorldHandler))
                .build();
    }

    public List<String> getPlayers() {
        return mcServer.getPlayerList().getPlayers().stream().map(player -> player.getName().getString()).collect(Collectors.toList());
    }

    public void start() throws Exception {
        LOGGER.info("Starting WebServer");
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}