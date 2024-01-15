package shwendel.yoggiessmp.enchantments.enchantments.speed;

import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class SpeedEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Speed";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "BOOTS" };
    }

    @Override
    public double getValue(int level) {
        return 0.04 * level;
    }

    @Override
    public void onApply(ItemStack item, int level) {

    }

    @Override
    public boolean hasLore() {
        return true;
    }

}
