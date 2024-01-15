package shwendel.yoggiessmp.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class SmeltingUtil {

    // Credit : K3ttle
    // https://www.spigotmc.org/threads/check-if-item-is-smeltable-and-get-resulting-smelted-item.326508/
    public static ItemStack getSmeltedItem(ItemStack item) {

        ItemStack result = null;

        Iterator<Recipe> iterator = Bukkit.recipeIterator();

        while(iterator.hasNext()) {

            Recipe recipe = iterator.next();

            if(!(recipe instanceof FurnaceRecipe)) {
                continue;
            }

            if (((FurnaceRecipe) recipe).getInput().getType() != item.getType()) {
                continue;
            }

            result = recipe.getResult();
            break;

        }

        if(result == null) {
            return null;
        }

        result.setAmount(item.getAmount());

        return result;
    }

}
