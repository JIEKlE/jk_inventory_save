package jiekie.inventorysave.event;

import jiekie.inventorysave.InventorySavePlugin;
import jiekie.inventorysave.util.ChatUtil;
import jiekie.inventorysave.manager.InventorySaveManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerEvent implements Listener {
    private final InventorySavePlugin plugin;

    public PlayerEvent(InventorySavePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        InventorySaveManager inventorySaveManager = plugin.getInventorySaveManager();
        ItemStack inventorySaveItem = inventorySaveManager.getInventorySaveItem();
        if(inventorySaveItem == null) return;

        Player player = e.getEntity();
        PlayerInventory inventory = player.getInventory();
        ItemStack[] items = inventory.getContents();

        for(int i = 0 ; i < items.length ; i++) {
            ItemStack item = items[i];
            if(!inventorySaveManager.compareItem(item)) continue;

            e.setKeepInventory(true);
            e.getDrops().clear();
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_SAVED);

            if(item.getAmount() > 1) item.setAmount(item.getAmount() - 1);
            else items[i] = null;

            inventory.setContents(items);
            return;
        }
    }
}
