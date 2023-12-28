package shwendel.yoggiessmp.counter.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shwendel.yoggiessmp.counter.YoggiesManager;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(!player.isOp()) {
            return true;
        }

        YoggiesManager.spawnLocation = player.getLocation();

        return true;
    }

}
