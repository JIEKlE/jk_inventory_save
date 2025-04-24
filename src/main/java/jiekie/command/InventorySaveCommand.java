package jiekie.command;

import jiekie.InventorySavePlugin;
import jiekie.util.ChatUtil;
import jiekie.util.SoundUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventorySaveCommand implements CommandExecutor {
    private final InventorySavePlugin plugin;

    public InventorySaveCommand(InventorySavePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            ChatUtil.notPlayer(sender);
            return true;
        }

        Player player = (Player) sender;
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
                getInventorySaveItem(player, args);
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
        if(inventorySaveItem == null || inventorySaveItem.getType() == Material.AIR) {
            ChatUtil.noItemInHand(player);
            return;
        }

        plugin.getInventorySaveManager().registerInventorySaveItem(inventorySaveItem);

        ChatUtil.registerInventorySaveItem(player);
        SoundUtil.playNoteBlockBell(player);
    }

    private void getInventorySaveItem(Player player, String[] args) {
        PlayerInventory inventory = player.getInventory();
        if(inventory.firstEmpty() == -1) {
            ChatUtil.inventoryFull(player);
            return;
        }

        ItemStack inventorySaveItem = plugin.getInventorySaveManager().getInventorySaveItem();
        if(inventorySaveItem == null) {
            ChatUtil.inventorySaveItemNotRegistered(player);
            return;
        }

        inventory.addItem(inventorySaveItem);

        ChatUtil.getInventorySavePaper(player);
        SoundUtil.playNoteBlockBell(player);
    }
}
