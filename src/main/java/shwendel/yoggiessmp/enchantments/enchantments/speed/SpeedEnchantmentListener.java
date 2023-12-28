package shwendel.yoggiessmp.enchantments.enchantments.speed;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class SpeedEnchantmentListener implements Listener {

    private static final float DEFAULT = 0.2f;

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getBoots();

        if(item != null && item.getType() != Material.AIR) {

            YoggiesEnchantment speed = YoggiesEnchantment.ENCHANTMENTS.get("Speed");
            int speedLevel = YoggiesEnchantment.getEnchantmentLevel(item, speed);

            float value = (float) (DEFAULT + speed.getValue(speedLevel));

            if(value > DEFAULT) {
                player.setWalkSpeed(value);
            }

        } else {

            if(player.getWalkSpeed() > DEFAULT) {
                player.setWalkSpeed(DEFAULT);
            }

        }

    }

}
