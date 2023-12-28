package shwendel.yoggiessmp.enchantments.enchantments.rocket_blast;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class RocketEnchantmentListener implements Listener {

    @EventHandler
    public void playerSneak(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        ItemStack chestplate = player.getInventory().getChestplate();

        if(!player.isSneaking() || chestplate == null || chestplate.getType() != Material.ELYTRA) {
            return;
        }

        YoggiesEnchantment rocketBlast = YoggiesEnchantment.ENCHANTMENTS.get("Rocket Blast");
        int rocketBlastLevel = YoggiesEnchantment.getEnchantmentLevel(chestplate, rocketBlast);

        if(rocketBlastLevel == 0) {
            return;
        }

        int rocketBlastValue = (int) rocketBlast.getValue(rocketBlastLevel);

        Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);

        firework.setAttachedTo(player);

    }

}
