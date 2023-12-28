package shwendel.yoggiessmp.enchantments.enchantments.drill;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.counter.YoggiesManager;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class DrillEnchantmentListener implements Listener {

    @EventHandler
    public void mineBlock(BlockBreakEvent event) {

        Location location = event.getBlock().getLocation();
        World world = location.getWorld();

        if(YoggiesManager.inShrineRange(location)) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item == null || item.getType() == Material.AIR || !player.isSneaking()) {
            return;
        }

        YoggiesEnchantment drill = YoggiesEnchantment.ENCHANTMENTS.get("Drill");
        int drillLevel = YoggiesEnchantment.getEnchantmentLevel(item, drill);

        if(drillLevel == 0) {
            return;
        }

        int drillValue = (int) drill.getValue(drillLevel);

        int locationX = location.getBlockX();
        int locationY = location.getBlockY();
        int locationZ = location.getBlockZ();

        for(int x = locationX - drillValue; x <= locationX + drillValue; x++) {

            for(int y = locationY - drillValue; y <= locationY + drillValue; y++) {

                for(int z = locationZ - drillValue; z <= locationZ + drillValue; z++) {

                    Block block = world.getBlockAt(x, y, z);

                    if(block.getType() != Material.BEDROCK) {
                        block.breakNaturally(item);
                    }

                }

            }

        }

    }

}
