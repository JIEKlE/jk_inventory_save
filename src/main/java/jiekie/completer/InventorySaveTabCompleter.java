package jiekie.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InventorySaveTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.hasPermission("jk.inventory_save.command")) return Collections.emptyList();

        if(args.length == 1)
            return Arrays.asList("등록", "받기", "도움말");

        return Collections.emptyList();
    }
}
