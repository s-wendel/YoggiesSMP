package shwendel.yoggiessmp.counter.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import shwendel.yoggiessmp.counter.YoggiesManager;

public class YoggiesChatListener implements Listener {

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event) {

        String message = event.getMessage();

        event.setMessage(ChatColor.translateAlternateColorCodes('&', message));

        message = message.toUpperCase();

        if(message.contains("YOGGIES")) {
            YoggiesManager.removeCounter(2);
        } else if(message.contains("YOGGIE")) {
            YoggiesManager.removeCounter(1);
        }

    }

}
