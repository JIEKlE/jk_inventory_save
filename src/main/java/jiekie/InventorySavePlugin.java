package jiekie;

import jiekie.command.InventorySaveCommand;
import jiekie.event.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventorySavePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // command
        getCommand("인벤세이브권").setExecutor(new InventorySaveCommand());

        // event
        getServer().getPluginManager().registerEvents(new PlayerEvent(this), this);

        getLogger().info("인벤 세이브 플러그인 by Jiekie");
        getLogger().info("Copyright © 2025 Jiekie. All rights reserved.");
    }

    @Override
    public void onDisable() {}
}
