package shwendel.yoggiessmp.enchantments.enchantments.experience;

import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class ExperienceEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Experience";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "PICKAXE", "SWORD", "AXE", "SHOVEL", "HOE" };
    }

    @Override
    public double getValue(int level) {
        return level;
    }

    @Override
    public void onApply(ItemStack item, int level) {

    }

    @Override
    public boolean hasLore() {
        return true;
    }

}
