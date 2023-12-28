package shwendel.yoggiessmp.enchantments.enchantments.rocket_blast;

import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class RocketBlastEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Rocket Blast";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "ELYTRA", };
    }

    @Override
    public double getValue(int level) {
        return level;
    }

}
