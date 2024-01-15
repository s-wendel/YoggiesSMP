package shwendel.yoggiessmp.enchantments.enchantments.smelting_touch;

import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class SmeltingTouchEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Smelting Touch";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "PICKAXE", "AXE", "SHOVEL", "HOE" };
    }

    @Override
    public double getValue(int level) {
        return 0;
    }

    @Override
    public void onApply(ItemStack item, int level) {

    }

    @Override
    public boolean hasLore() {
        return true;
    }

}
