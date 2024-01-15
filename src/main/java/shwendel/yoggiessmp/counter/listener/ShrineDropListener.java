package shwendel.yoggiessmp.counter.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import shwendel.yoggiessmp.counter.YoggiesManager;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

import java.util.concurrent.ThreadLocalRandom;

public class ShrineDropListener implements Listener {

    /**private static ThreadLocalRandom random = ThreadLocalRandom.current();

    @EventHandler
    public void dropItem(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        Item item = event.getItemDrop();

        Location location = item.getLocation();

        if(YoggiesManager.inShrineRange(location)) {

            ItemStack itemStack = item.getItemStack();
            ItemMeta itemMeta = itemStack.getItemMeta();

            Material type = itemStack.getType();

            int counter = 0;

            switch(type) {

                case NETHERITE_SCRAP:

                    counter += 2;
                    break;

                case NETHERITE_INGOT:

                    counter += 9;
                    break;

                case DIAMOND:

                    counter += 1;
                    break;

                default:

                    if(type == YoggiesManager.YOGGIES_FRAGMENT_MATERIAL && itemStack.hasItemMeta() && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals(YoggiesManager.YOGGIES_FRAGMENT_NAME)) {

                        double number = random.nextDouble(100);

                        ItemStack drop;

                        if(number <= 20) {
                            drop = YoggiesEnchantment.ENCHANTMENTS.get("Drill").getItem(1);
                        } else if(number <= 27) {
                            drop = YoggiesEnchantment.ENCHANTMENTS.get("Africa").getItem(1);
                        } else if(number <= 29) {
                            drop = YoggiesEnchantment.ENCHANTMENTS.get("Rocket Blast").getItem(1);
                        } else if(number <= 65) {
                            drop = YoggiesEnchantment.ENCHANTMENTS.get("Speed").getItem(1);
                        } else if(number <= 75) {
                            drop = new ItemStack(Material.NETHERITE_INGOT);
                        } else {
                            drop = new ItemStack(Material.DIAMOND_BLOCK);
                        }

                        player.getInventory().addItem(drop);

                    }

                    break;

            }

            YoggiesManager.addCounter(counter);

            item.remove();

        }

    }*/

}
