package shwendel.yoggiessmp.enchantments.enchantments.africa;

import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class AfricaEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Africa";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "TRIDENT" };
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
