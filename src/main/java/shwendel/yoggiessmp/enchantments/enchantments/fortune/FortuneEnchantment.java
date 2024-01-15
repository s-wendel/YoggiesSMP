package shwendel.yoggiessmp.enchantments.enchantments.fortune;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import shwendel.yoggiessmp.enchantments.YoggiesEnchantment;

public class FortuneEnchantment extends YoggiesEnchantment {

    @Override
    public String getName() {
        return "Fortune";
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public String[] getApplicableTypes() {
        return new String[] { "ENCHANTED_BOOK", "PICKAXE" };
    }

    @Override
    public double getValue(int level) {
        return 1;
    }

    @Override
    public void onApply(ItemStack item, int level) {
        item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, level);
    }

    @Override
    public boolean hasLore() {
        return false;
    }

}
