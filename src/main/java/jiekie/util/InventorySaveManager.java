package jiekie.util;

import jiekie.InventorySavePlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class InventorySaveManager {
    private final InventorySavePlugin plugin;
    private ItemStack inventorySaveItem;
    private final List<String> inventorySaveItemLore = List.of(
            " "
            , ChatColor.DARK_GRAY + "사망 시 해당 아이템을 소지하고 있을 경우 인벤토리가 보호됩니다."
    );

    public InventorySaveManager(InventorySavePlugin plugin) {
        this.plugin = plugin;
    }

    public void load() {
        FileConfiguration config = plugin.getConfig();
        String encodedItem = config.getString("inventory_save_item");
        if(encodedItem == null) return;
        
        try {
            inventorySaveItem = itemFromBase64(encodedItem);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            plugin.getLogger().info("인벤 세이브 아이템 불러오기 실패");
        }
    }

    public void registerInventorySaveItem(ItemStack inventorySaveItem) {
        inventorySaveItem = inventorySaveItem.clone();
        ItemMeta meta = inventorySaveItem.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "인벤 세이브권");
        meta.setLore(inventorySaveItemLore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        inventorySaveItem.setItemMeta(meta);
        inventorySaveItem.setAmount(1);

        this.inventorySaveItem = inventorySaveItem;
    }

    public ItemStack getInventorySaveItem() {
        return inventorySaveItem;
    }

    public boolean compareItem(ItemStack item) {
        if(item == null || item.getType() != inventorySaveItem.getType()) return false;

        ItemMeta meta = item.getItemMeta();
        if(meta == null || !meta.hasDisplayName()) return false;

        // display name
        ItemMeta inventorySaveItemMeta = inventorySaveItem.getItemMeta();
        if(!meta.getDisplayName().equals(inventorySaveItemMeta.getDisplayName())) return false;

        // lore
        if(meta.getLore() == null || !meta.getLore().equals(inventorySaveItemLore)) return false;

        // custom model data
        if(meta.hasCustomModelData() != inventorySaveItemMeta.hasCustomModelData()) return false;
        if(inventorySaveItemMeta.hasCustomModelData()) {
            if(!meta.hasCustomModelData() || meta.getCustomModelData() != inventorySaveItemMeta.getCustomModelData()) return false;
        }

        return true;
    }

    public void save() {
        if(inventorySaveItem == null) return;

        try {
            String encodedItem = itemStackToBase64(inventorySaveItem);
            FileConfiguration config = plugin.getConfig();
            config.set("inventory_save_item", encodedItem);

            plugin.saveConfig();

        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().info("인벤 세이브 아이템 저장 실패");
        }
    }

    private String itemStackToBase64(ItemStack item) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

        dataOutput.writeObject(item);
        dataOutput.close();
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    private ItemStack itemFromBase64(String base64) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(base64);
        BukkitObjectInputStream inputStream = new BukkitObjectInputStream(new ByteArrayInputStream(data));
        ItemStack item = (ItemStack) inputStream.readObject();
        inputStream.close();
        return item;
    }
}
