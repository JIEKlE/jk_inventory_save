package jiekie.inventorysave.command;

import jiekie.inventorysave.InventorySavePlugin;
import jiekie.inventorysave.util.ChatUtil;
import jiekie.inventorysave.util.SoundUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class InventorySaveCommand implements CommandExecutor {
    private final InventorySavePlugin plugin;

    public InventorySaveCommand(InventorySavePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player player)) {
            ChatUtil.notPlayer(sender);
            return true;
        }

        if(!player.isOp()) {
            ChatUtil.notOp(player);
            return true;
        }

        if(args == null || args.length == 0) {
            ChatUtil.commandHelper(player);
            return true;
        }

        switch (args[0]) {
            case "등록":
                registerInventorySaveItem(player);
                break;

            case "받기":
                getInventorySaveItem(player);
                break;

            case "도움말":
                ChatUtil.commandList(player);
                break;

            default:
                ChatUtil.commandHelper(player);
                break;
        }

        return true;
    }

    private void registerInventorySaveItem(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack inventorySaveItem = inventory.getItemInMainHand();
        if(inventorySaveItem.getType() == Material.AIR) {
            ChatUtil.showMessage(player, ChatUtil.NO_ITEM);
            return;
        }

        plugin.getInventorySaveManager().registerInventorySaveItem(inventorySaveItem);

        ChatUtil.showMessage(player, ChatUtil.REGISTER_INVENTORY_SAVE_ITEM);
        SoundUtil.playNoteBlockBell(player);
    }

    private void getInventorySaveItem(Player player) {
        PlayerInventory inventory = player.getInventory();
        if(inventory.firstEmpty() == -1) {
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_FULL);
            return;
        }

        ItemStack inventorySaveItem = plugin.getInventorySaveManager().getInventorySaveItem();
        if(inventorySaveItem == null) {
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_SAVE_ITEM_NOT_REGISTERED);
            return;
        }

        inventory.addItem(inventorySaveItem);

        ChatUtil.showMessage(player, ChatUtil.GET_INVENTORY_SAVE_PAPER);
        SoundUtil.playNoteBlockBell(player);
    }
}
