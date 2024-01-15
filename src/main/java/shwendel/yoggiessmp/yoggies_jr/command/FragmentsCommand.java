package shwendel.yoggiessmp.yoggies_jr.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shwendel.yoggiessmp.yoggies_jr.FragmentGUI;

public class FragmentsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        new FragmentGUI(player).open();

        return true;
    }

}
