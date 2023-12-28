package shwendel.yoggiessmp.counter.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import shwendel.yoggiessmp.counter.YoggiesManager;

public class YoggiesKillListener implements Listener {

    @EventHandler
    public void yoggiesKill(EntityDeathEvent event) {

        Entity entity = event.getEntity();

        if(!(entity instanceof MagmaCube)) {
            return;
        }

        MagmaCube magmaCube = (MagmaCube) entity;

        Player killer = magmaCube.getKiller();

        if(killer == null) {
            return;
        }

        YoggiesManager.removeCounter(1);

    }

}
