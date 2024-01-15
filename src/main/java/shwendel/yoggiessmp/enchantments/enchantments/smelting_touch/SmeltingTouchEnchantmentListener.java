package shwendel.yoggiessmp.enchantments.enchantments.smelting_touch;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.counter.YoggiesManager;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

import java.util.Arrays;

public class SmeltingTouchEnchantmentListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockBreak(BlockBreakEvent event) {

        Block block = event.getBlock();

        Location location = block.getLocation();

        if(YoggiesManager.inShrineRange(location)) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item == null || item.getType() == Material.AIR) {
            return;
        }

        YoggiesEnchantment.mineBlocks(Arrays.asList(block), item);

    }

}
