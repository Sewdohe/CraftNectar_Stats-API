package com.example.examplemod;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import net.minecraft.server.MinecraftServer;

import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import java.util.List;
import java.util.stream.Collectors;

public class WebServer {

    private final Undertow server;
//    private MinecraftServer mcServer;
    private static final Logger LOGGER = LogUtils.getLogger();
    private final MinecraftServer mcServer;

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
        exchange.getResponseSender().send(getPlayers().toString());
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

    public List<PlayerData> getPlayers() {
        return mcServer.getPlayerList().getPlayers().stream().map(PlayerData::new).collect(Collectors.toList());
    }

    public void start() throws Exception {
        LOGGER.info("Starting WebServer");
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public List<ServerPlayer> getOnlinePlayers() {
        return mcServer.getPlayerList().getPlayers();
    }

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinLevelEvent event) {
        String name = event.getEntity().getName().toString();
        LOGGER.warn("=====================================");
        LOGGER.warn("Player " + name + " joined the server");
        LOGGER.warn("=====================================");
    }
}