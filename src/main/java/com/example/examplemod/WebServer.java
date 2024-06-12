package com.example.examplemod;

import com.google.gson.GsonBuilder;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.util.HttpString;
import net.minecraft.server.MinecraftServer;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import com.google.gson.Gson;

import java.util.Collection;

public class WebServer {

    public final Undertow server;
    private static final Logger LOGGER = LogUtils.getLogger();
    public final MinecraftServer mcServer;


    // Create a handler for the hello world endpoint
    HttpHandler helloWorldHandler = exchange -> {
        LOGGER.warn("/hello endpoint hit!!!");
        exchange.getResponseSender().send("Hello, World!");
    };
    HttpHandler pingHandler = exchange -> {
        LOGGER.warn("/ping endpoint hit!!!");
        exchange.getResponseSender().send("PING!");
    };
    // Create a handler to send current player list
    HttpHandler playerListHandler = exchange -> {
        exchange.getResponseHeaders().add(new HttpString("Access-Control-Allow-Origin"), "*");
        exchange.getResponseSender().send(getPlayers());
    };



    public WebServer(int port, MinecraftServer mcServer) {
        this.server = Undertow.builder()
                .addHttpListener(port, "0.0.0.0")
                .setHandler(Handlers.path().addPrefixPath("/", pingHandler))
                .setHandler(Handlers.path().addPrefixPath("/hello", helloWorldHandler))
                .setHandler(Handlers.path().addPrefixPath("/players", playerListHandler))
                .build();

//        this.mcServer = mcServer;
        this.mcServer = ServerLifecycleHooks.getCurrentServer();
        LOGGER.info("WebServer created on port " + port);
    }

    public String getPlayers() {
        Gson gson = new Gson();
        Collection<PlayerData> players = mcServer.getPlayerList().getPlayers().stream().map(PlayerData::new).toList();
        LOGGER.info("Attempting parsing players: {}", players);
        for(PlayerData player : players) {
            LOGGER.info(player.getPlayerName() + "with " + player.getTotalLevels() + " levels");
        }

        return gson.toJson(players);
    }

    public void start() throws Exception {
        LOGGER.info("Starting WebServer");
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinLevelEvent event) {
        String name = event.getEntity().getName().toString();
        LOGGER.warn("=====================================");
        LOGGER.warn("Player " + name + " joined the server");
        LOGGER.warn("=====================================");
    }
}