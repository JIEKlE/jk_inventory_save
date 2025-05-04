package jiekie.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {
    /* error */
    public static final String NO_ITEM = getXPrefix() + "손에 아이템을 들고 설정해주시기 바랍니다.";
    public static final String INVENTORY_FULL = getXPrefix() + "인벤토리가 가득 찼습니다. 인벤토리를 1칸 이상 비워주시기 바랍니다.";
    public static final String INVENTORY_SAVE_ITEM_NOT_REGISTERED = getXPrefix() + "인벤 세이브권이 등록되지 않았습니다.";

    /* feedback */
    public static final String REGISTER_INVENTORY_SAVE_ITEM = getCheckPrefix() + "인벤 세이브권을 등록했습니다.";
    public static final String GET_INVENTORY_SAVE_PAPER = getCheckPrefix() + "인벤 세이브권을 지급받았습니다.";
    public static final String INVENTORY_SAVED = getWarnPrefix() + "인벤 세이브권을 사용하여 인벤토리가 보호되었습니다.";

    /* prefix */
    public static String getCheckPrefix() {
        return "\uA001 ";
    }

    public static String getXPrefix() {
        return "\uA002 ";
    }

    public static String getWarnPrefix() {
        return "\uA003 ";
    }

    public static void showMessage(Player player, String message) {
        player.sendMessage(message);
    }

    /* validate */
    public static void notPlayer(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "플레이어가 아닙니다.");
    }

    public static void notOp(Player player) {
        player.sendMessage(getWarnPrefix() + "권한이 없습니다.");
    }

    /* command */
    public static void commandHelper(Player player) {
        player.sendMessage(getWarnPrefix() + "/인벤세이브권 도움말" + ChatColor.GRAY + " : 사용 가능한 명령어를 확인할 수 있습니다.");
    }

    public static void commandList(Player player) {
        player.sendMessage("");
        player.sendMessage(getWarnPrefix() + "인벤세이브권 명령어 목록");
        player.sendMessage("　　　① /인벤세이브권 등록");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 손에 든 아이템을 인벤 세이브권으로 등록합니다.");
        player.sendMessage("　　　② /인벤세이브권 받기");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 인벤 세이브권 아이템을 받습니다.");
        player.sendMessage("　　　③ /인벤세이브권 도움말");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 사용 가능한 명령어를 확인할 수 있습니다.");
        player.sendMessage("");
    }
}
