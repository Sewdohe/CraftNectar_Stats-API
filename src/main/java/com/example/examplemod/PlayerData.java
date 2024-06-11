package com.example.examplemod;

import net.minecraft.server.level.ServerPlayer;
import harmonised.pmmo.api.APIUtils;
import harmonised.pmmo.core.Core;
import net.minecraftforge.fml.LogicalSide;

import java.util.UUID;

public class PlayerData {
    Core core = Core.get(LogicalSide.SERVER);
//    private final ServerPlayer player;
    private String playerName;
    private UUID uuid;
    private int totalLevels = 0;
    private String avatarUrl = "";

    public PlayerData(ServerPlayer player) {
//        this.player = player;
        this.playerName = player.getName().getString();
        this.uuid = player.getUUID();
        this.totalLevels = core.getData()
                .getXpMap(player.getUUID())
                .values()
                .stream().mapToInt(core.getData()::getLevelFromXP).sum();

    }
}
