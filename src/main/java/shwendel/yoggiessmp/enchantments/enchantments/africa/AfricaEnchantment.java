package shwendel.yoggiessmp.enchantments.enchantments.africa;

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

}
