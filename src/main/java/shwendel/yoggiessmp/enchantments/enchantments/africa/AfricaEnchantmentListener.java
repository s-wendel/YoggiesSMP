package shwendel.yoggiessmp.enchantments.enchantments.africa;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class AfricaEnchantmentListener implements Listener {

    @EventHandler
    public void playerMove(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item == null || item.getType() == Material.AIR || !player.isSneaking()) {
            return;
        }

        YoggiesEnchantment africa = YoggiesEnchantment.ENCHANTMENTS.get("Africa");
        int africaLevel = YoggiesEnchantment.getEnchantmentLevel(item, africa);

        if(africaLevel == 0) {
            return;
        }

        World world = player.getWorld();

        boolean isStorming = world.hasStorm();

        System.out.println(isStorming);

        world.setStorm(!isStorming);

        System.out.println(world.hasStorm());

        player.sendMessage("The world is " + (!isStorming ? "now" : "no longer") + " raining");

    }

}
