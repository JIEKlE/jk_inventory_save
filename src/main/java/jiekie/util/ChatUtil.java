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

    public static void noItemInHand(Player player) {
        player.sendMessage(getWarnPrefix() + "손에 아이템을 들고 설정해주시기 바랍니다.");
    }

    public static void inventoryFull(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤토리가 가득 찼습니다. 인벤토리를 1칸 이상 비워주시기 바랍니다.");
    }

    public static void inventorySaveItemNotRegistered(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤 세이브권이 등록되지 않았습니다.");
    }

    public static void registerInventorySaveItem(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤 세이브권을 등록했습니다.");
    }
    
    public static void getInventorySavePaper(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤 세이브권을 지급받았습니다.");
    }

    public static void inventorySaved(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤 세이브권을 사용하여 인벤토리가 보호되었습니다.");
    }

    public static void commandHelper(Player player) {
        player.sendMessage(getWarnPrefix() + "/인벤세이브권 도움말" + ChatColor.GRAY + " : 사용 가능한 명령어를 확인할 수 있습니다.");
    }

    public static void commandList(Player player) {
        player.sendMessage(getWarnPrefix() + "인벤세이브권 명령어 목록");
        player.sendMessage("　　　① /인벤세이브권 등록");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 손에 든 아이템을 인벤 세이브권으로 등록합니다.");
        player.sendMessage("　　　② /인벤세이브권 받기");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 인벤 세이브권 아이템을 받습니다.");
        player.sendMessage("　　　③ /인벤세이브권 도움말");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 사용 가능한 명령어를 확인할 수 있습니다.");
    }
}
