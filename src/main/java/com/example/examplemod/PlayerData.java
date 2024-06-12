package com.example.examplemod;

import net.minecraft.server.level.ServerPlayer;
import harmonised.pmmo.api.APIUtils;
import harmonised.pmmo.core.Core;
import net.minecraftforge.fml.LogicalSide;

public class PlayerData {
    private transient Core core = Core.get(LogicalSide.SERVER);
    private String playerName = "DefaultPlayer";
    private String uuid = "00000-0000-00000-00000";
    private int totalLevels = 0;
    private int mining = 0;
    private int crafting = 0;
    private int woodcutting = 0;
    private int excavation = 0;
    private int agility = 0;
    private int combat = 0;
    private int mechanics = 0;
    private int smithing = 0;
    private int endurance = 0;
    private int engineering = 0;
    private int building = 0;
    private int cooking = 0;
    private float health = 0f;
    private float maxHealth = 20f;
    private String avatarUrl = "https://crafatar.com/avatars/" + "notch";

    public PlayerData(ServerPlayer player) {
        // this.player = player;
        this.playerName = player.getName().getString();
        this.uuid = player.getUUID().toString();
        this.health = player.getHealth();
        this.maxHealth = player.getMaxHealth();
        this.totalLevels = core.getData()
                .getXpMap(player.getUUID())
                .values()
                .stream().mapToInt(core.getData()::getLevelFromXP).sum();
        this.mining = core.getData()
                .getPlayerSkillLevel("mining", player.getUUID());
        this.crafting = core.getData()
                .getPlayerSkillLevel("crafting", player.getUUID());
        this.woodcutting = core.getData()
                .getPlayerSkillLevel("woodcutting", player.getUUID());
        this.excavation = core.getData()
                .getPlayerSkillLevel("excavation", player.getUUID());
        this.agility = core.getData()
                .getPlayerSkillLevel("agility", player.getUUID());
        this.combat = core.getData()
                .getPlayerSkillLevel("combat", player.getUUID());
        this.mechanics = core.getData()
                .getPlayerSkillLevel("mechanics", player.getUUID());
        this.smithing = core.getData()
                .getPlayerSkillLevel("smithing", player.getUUID());
        this.endurance = core.getData()
                .getPlayerSkillLevel("endurance", player.getUUID());
        this.engineering = core.getData()
                .getPlayerSkillLevel("engineering", player.getUUID());
        this.building = core.getData()
                .getPlayerSkillLevel("building", player.getUUID());
        this.cooking = core.getData()
                .getPlayerSkillLevel("cooking", player.getUUID());

        this.avatarUrl = "https://crafatar.com/avatars/" + this.uuid + "?overlay";
    }
    public PlayerData() {}

    public String getPlayerName() {
        return playerName;
    }
    public String getUuid() {
        return uuid;
    }
    public int getTotalLevels() {
        return totalLevels;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public int getCombatLevel() {
        return combat;
    }

    public int getAgility() {
        return agility;
    }

    public int getCombat() {
        return combat;
    }

    public int getMechanics() {
        return mechanics;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getEngineering() {
        return engineering;
    }

    public int getBuilding() {
        return building;
    }

    public int getCooking() {
        return cooking;
    }

    public int getMining() {
        return mining;
    }

    public int getCrafting() {
        return crafting;
    }

    public int getWoodcutting() {
        return woodcutting;
    }

    public int getExcavation() {
        return excavation;
    }

    public int getSmithing() {
        return smithing;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}

