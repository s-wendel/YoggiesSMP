package shwendel.yoggiessmp.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.reflections.Reflections;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.util.RomanNumeral;

import java.util.*;

public abstract class YoggiesEnchantment {

    public static final Map<String, YoggiesEnchantment> ENCHANTMENTS = new HashMap<>();

    private static final YoggiesSMP INSTANCE = YoggiesSMP.getInstance();

    static {

        Set<Class<? extends YoggiesEnchantment>> classList = new Reflections("shwendel.yoggiessmp.enchantments.enchantments").getSubTypesOf(YoggiesEnchantment.class);

        for(Class<? extends YoggiesEnchantment> clazz : classList) {

            try {

                YoggiesEnchantment enchantment = clazz.getConstructor().newInstance();

                ENCHANTMENTS.put(enchantment.getName(), enchantment);

            } catch(Exception exception) {

                exception.printStackTrace();

            }

        }

    }

    public abstract String getName();
    public abstract int getMaxLevel();

    /**
     * Return a list of applicable material types, e.g. PICKAXE. Checks the end of a material for each applicable type when applying enchantments
     * @return The list of applicable materials
     */
    public abstract String[] getApplicableTypes();

    /**
     * Returns a value used in the Enchantment, e.g. Drill has a value of 2 at Drill II because it's a 2x2x2
     * @param level The level
     * @return The value
     */
    public abstract double getValue(int level);

    public static ItemStack setEnchantmentLevel(ItemStack item, YoggiesEnchantment enchantment, int level) {

        ItemStack newItem = item.clone();

        ItemMeta itemMeta = newItem.getItemMeta();

        itemMeta.getPersistentDataContainer().set(getNamespacedKey(enchantment), PersistentDataType.INTEGER, level);

        String enchantmentName = enchantment.getName();
        String enchantmentLine = getEnchantmentLine(enchantment, level);

        if(itemMeta.hasLore()) {

            int index = 0;
            List<String> lore = itemMeta.getLore();
            boolean appliedEnchantment = false;

            while(index < lore.size() && !appliedEnchantment) {

                if(lore.get(index).contains(enchantmentName)) {
                    lore.set(index, enchantmentLine);
                    appliedEnchantment = true;
                }

                index++;

            }

            if(!appliedEnchantment) {
                lore.add(enchantmentLine);
            }

            itemMeta.setLore(lore);

        } else {

            itemMeta.setLore(Arrays.asList(enchantmentLine));

        }

        newItem.setItemMeta(itemMeta);

        return newItem;
    }

    public static int getEnchantmentLevel(ItemStack item, YoggiesEnchantment enchantment) {
        return item.getItemMeta().getPersistentDataContainer().getOrDefault(getNamespacedKey(enchantment), PersistentDataType.INTEGER, 0);
    }

    private static NamespacedKey getNamespacedKey(YoggiesEnchantment enchantment) {
        return new NamespacedKey(INSTANCE, enchantment.getName().replaceAll(" ", "_"));
    }

    private static String getEnchantmentLine(YoggiesEnchantment enchantment, int level) {
        return ChatColor.GRAY + enchantment.getName() + " " + RomanNumeral.toRoman(level);
    }

    public ItemStack getItem(int level) {

        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);

        item = setEnchantmentLevel(item, this, level);

        return item;
    }

    public boolean isApplicableType(Material material) {

        String name = material.toString();

        for(String applicable : getApplicableTypes()) {
            if(name.endsWith(applicable)) {
                return true;
            }
        }

        return false;
    }

    public boolean isApplicableType(ItemStack item) {
        return isApplicableType(item.getType());
    }

}
