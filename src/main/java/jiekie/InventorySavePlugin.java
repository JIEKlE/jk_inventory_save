package jiekie;

import jiekie.command.InventorySaveCommand;
import jiekie.completer.InventorySaveTabCompleter;
import jiekie.event.PlayerEvent;
import jiekie.manager.InventorySaveManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventorySavePlugin extends JavaPlugin {
    private InventorySaveManager inventorySaveManager;

    @Override
    public void onEnable() {
        // config
        saveDefaultConfig();
        reloadConfig();

        inventorySaveManager = new InventorySaveManager(this);
        inventorySaveManager.load();

        // command
        getCommand("인벤세이브권").setExecutor(new InventorySaveCommand(this));

        // completer
        getCommand("인벤세이브권").setTabCompleter(new InventorySaveTabCompleter());

        // event
        getServer().getPluginManager().registerEvents(new PlayerEvent(this), this);

        getLogger().info("인벤 세이브 플러그인 by Jiekie");
        getLogger().info("Copyright © 2025 Jiekie. All rights reserved.");
    }

    public InventorySaveManager getInventorySaveManager() {
        return inventorySaveManager;
    }

    @Override
    public void onDisable() {
        inventorySaveManager.save();
    }
}
