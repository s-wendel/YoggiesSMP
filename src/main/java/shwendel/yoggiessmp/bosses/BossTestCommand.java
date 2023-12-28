package shwendel.yoggiessmp.bosses;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shwendel.yoggiessmp.bosses.abilities.ShockwaveAbility;
import shwendel.yoggiessmp.bosses.abilities.SpikeAbility;

public class BossTestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        new SpikeAbility(Material.SLIME_BLOCK).run(((Player) sender).getTargetBlock(null, 10).getLocation());

        return true;
    }

}
