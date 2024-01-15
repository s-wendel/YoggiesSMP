package shwendel.yoggiessmp.enchantments.listener;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

import java.util.Map;

public class EnchantmentAnvilListener implements Listener {

    @EventHandler
    public void prepareAnvil(PrepareAnvilEvent event) {

        AnvilInventory inventory = event.getInventory();

        ItemStack first = inventory.getItem(0);
        ItemStack second = inventory.getItem(1);
        int cost = 0;

        if(first == null || second == null) {
            return;
        }

        ItemStack result = null;

        for(YoggiesEnchantment enchantment : YoggiesEnchantment.ENCHANTMENTS.values()) {

            if(!enchantment.isApplicableType(first)) {
                continue;
            }

            int firstLevel = YoggiesEnchantment.getEnchantmentLevel(first, enchantment);
            int secondLevel = YoggiesEnchantment.getEnchantmentLevel(second, enchantment);

            if(secondLevel <= 0) {
                continue;
            }

            int newLevel;

            if(firstLevel > secondLevel) {
                newLevel = firstLevel;
            } else if(firstLevel == secondLevel) {
                newLevel = firstLevel + 1;
            } else {
                newLevel = secondLevel;
            }

            if(newLevel > enchantment.getMaxLevel()) {
                continue;
            }

            result = YoggiesEnchantment.setEnchantmentLevel(first, enchantment, newLevel);

            enchantment.onApply(result, newLevel);

        }

        if(result != null) {

            addEnchantedBookEnchantments(second, result);

            event.setResult(result);

            int finalCost = cost;

            YoggiesSMP instance = YoggiesSMP.getInstance();

            // Required to work
            instance.getServer().getScheduler().runTask(instance, () -> event.getInventory().setRepairCost(finalCost));

        }

    }

    /**
     * Add enchantments from an original item to another
     * @param book The enchantment book
     * @param item The item receiving enchantments
     */
    private void addEnchantedBookEnchantments(ItemStack book, ItemStack item) {

        EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) book.getItemMeta();

        if(enchantmentStorageMeta.hasStoredEnchants()) {

            for(Map.Entry<Enchantment, Integer> enchantment : enchantmentStorageMeta.getStoredEnchants().entrySet()) {

                item.addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());

            }

        }

    }

}
