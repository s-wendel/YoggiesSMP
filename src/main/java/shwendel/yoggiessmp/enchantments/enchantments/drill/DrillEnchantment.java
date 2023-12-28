package shwendel.yoggiessmp.enchantments.enchantments.drill;

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
        return level;
    }

}
