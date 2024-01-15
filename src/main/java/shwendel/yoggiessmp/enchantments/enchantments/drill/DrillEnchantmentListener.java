package shwendel.yoggiessmp.enchantments.enchantments.drill;

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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DrillEnchantmentListener implements Listener {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final int RANGE = 1; // Radius of the cube

    @EventHandler(priority = EventPriority.HIGHEST)
    public void mineBlock(BlockBreakEvent event) {

        Location location = event.getBlock().getLocation();
        World world = location.getWorld();

        if(YoggiesManager.inShrineRange(location)) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item == null || item.getType() == Material.AIR) {
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

        double number = random.nextDouble(100);

        List<Block> blocks = new ArrayList<>();

        if(number <= drillValue) {

            for(int x = locationX - RANGE; x <= locationX + RANGE; x++) {

                for(int y = locationY - RANGE; y <= locationY + RANGE; y++) {

                    for(int z = locationZ - RANGE; z <= locationZ + RANGE; z++) {

                        Block block = world.getBlockAt(x, y, z);

                        if(block.getType() != Material.BEDROCK) {
                            blocks.add(block);
                        }

                    }

                }

            }

        }

        YoggiesEnchantment.mineBlocks(blocks, item);

    }

}
