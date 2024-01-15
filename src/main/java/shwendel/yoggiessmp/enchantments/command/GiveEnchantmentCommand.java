package shwendel.yoggiessmp.enchantments.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;
import shwendel.yoggiessmp.util.CaseUtil;

public class GiveEnchantmentCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!sender.isOp()) {
            return true;
        }

        // /giveenchantment <player> <type> <level>

        ItemStack item = YoggiesEnchantment.ENCHANTMENTS.get(CaseUtil.toProperCase(args[1])).getItem(Integer.parseInt(args[2]));

        Player player = Bukkit.getPlayer(args[0]);

        player.getInventory().addItem(item);

        return true;
    }
}
