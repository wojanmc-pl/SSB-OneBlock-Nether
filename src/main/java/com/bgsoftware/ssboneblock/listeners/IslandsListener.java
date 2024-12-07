package com.bgsoftware.ssboneblock.listeners;

import com.bgsoftware.ssboneblock.NetherBlockModule;
import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public final class IslandsListener implements Listener {

    private final NetherBlockModule plugin;

    public IslandsListener(NetherBlockModule plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandDelete(IslandDisbandEvent e) {
        plugin.getPhasesHandler().getDataStore().removeIsland(e.getIsland());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onIslandCreate(IslandCreateEvent e) {
        if (plugin.getPhasesHandler().canHaveOneBlock(e.getIsland()))
            plugin.getPhasesHandler().runNextAction(e.getIsland(), e.getPlayer().asPlayer());
    }


}
