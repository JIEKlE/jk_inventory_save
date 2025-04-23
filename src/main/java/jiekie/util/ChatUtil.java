package jiekie.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {
    public static String getWarnPrefix() {
        return "[ " + ChatColor.YELLOW + "❗" + ChatColor.WHITE + " ] ";
    }

    public static void notPlayer(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "플레이어가 아닙니다.");
    }

    public static void notOp(Player player) {
        player.sendMessage(getWarnPrefix() + "권한이 없습니다.");
    }

    public static void inventoryFull(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤토리가 가득 찼습니다. 인벤토리를 1칸 이상 비워주시기 바랍니다.");
    }

    public static void getInventorySavePaper(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤 세이브권을 지급받았습니다.");
    }

    public static void inventorySaved(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤 세이브권을 사용하여 인벤토리가 보호되었습니다.");
    }
}
