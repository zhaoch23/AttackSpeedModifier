package com.zhaoch23.attackspeedmodifier;

import com.zhaoch23.attackspeedmodifier.api.AttackSpeedUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AttackSpeedModifier extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerLoggedIn(PlayerLoginEvent event) {
        AttackSpeedUtils.addCustomAttackSpeedModifier(event.getPlayer(), 10, AttributeModifier.Operation.ADD_NUMBER);
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event) {
        AttackSpeedUtils.removeCustomAttackSpeedModifier(event.getPlayer(),  AttributeModifier.Operation.ADD_NUMBER);
    }
}
