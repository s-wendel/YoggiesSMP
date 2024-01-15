package shwendel.yoggiessmp.bosses;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shwendel.yoggiessmp.bosses.bosses.TheGreenBoss;

public class BossTestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        Player player = (Player) sender;

        BossMemory bossMemory = new BossMemory(new TheGreenBoss());

        bossMemory.spawn(player.getLocation());

        return true;
    }

}
