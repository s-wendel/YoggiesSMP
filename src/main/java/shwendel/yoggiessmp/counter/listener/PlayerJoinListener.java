package shwendel.yoggiessmp.counter.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import shwendel.yoggiessmp.counter.YoggiesManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        YoggiesManager.bossBar.addPlayer(player);

    }

}
