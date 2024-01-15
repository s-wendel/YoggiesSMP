package shwendel.yoggiessmp.enchantments.enchantments.drill;

import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class DrillEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Drill";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "PICKAXE", "SHOVEL" };
    }

    @Override
    public double getValue(int level) {
        return level * 33.3;
    }

    @Override
    public void onApply(ItemStack item, int level) {

    }

    @Override
    public boolean hasLore() {
        return true;
    }

}
