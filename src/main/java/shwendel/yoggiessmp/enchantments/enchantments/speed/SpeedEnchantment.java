package shwendel.yoggiessmp.enchantments.enchantments.speed;

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

}
