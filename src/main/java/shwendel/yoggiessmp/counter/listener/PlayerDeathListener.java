package shwendel.yoggiessmp.counter.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import shwendel.yoggiessmp.counter.YoggiesManager;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {

        YoggiesManager.removeCounter(1);

    }

}
