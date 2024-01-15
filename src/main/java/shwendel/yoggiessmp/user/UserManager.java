package shwendel.yoggiessmp.user;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class UserManager {

    private static final Map<UUID, User> USERS = new HashMap<>();

    private static final String YOGGIES_FRAGMENTS_SECTION = "yoggies_fragments";
    private static final String DONATION_REMAINDER_SECTION = "donation_remainder";
    private static final String DONATION_REMNANTS_SECTION = "donation_remnants";

    /**
     * Load a user, returns the user loaded and saves it to memory
     * @param player The player
     * @return The user I thin
     */
    private static User loadUser(Player player) {

        UUID uuid = player.getUniqueId();

        UserConfig config = new UserConfig(player);
        FileConfiguration file = config.getFile();

        final String SECTION = uuid + ".";

        ItemStack[] donationRemnants = new ItemStack[54]; // Max size of GUI

        final String USER_DONATION_REMNANTS_SECTION = SECTION + DONATION_REMNANTS_SECTION;

        if(file.isSet(USER_DONATION_REMNANTS_SECTION)) {

            for(String indexString : file.getConfigurationSection(USER_DONATION_REMNANTS_SECTION).getKeys(false)) {

                int index = Integer.parseInt(indexString);

                donationRemnants[index] = file.getItemStack(USER_DONATION_REMNANTS_SECTION + "." + indexString);

            }

        }

        User user = new User(
                player,
                file.getInt(SECTION + YOGGIES_FRAGMENTS_SECTION, 0),
                file.getInt(SECTION + DONATION_REMAINDER_SECTION, 0),
                donationRemnants
        );

        USERS.put(uuid, user);

        return user;
    }

    public static void saveUser(Player player) {
        saveUser(UserManager.getUser(player));
    }

    /**
     * Saves a user to config
     * @param user The user
     */
    public static void saveUser(User user) {

        Player player = user.getPlayer();
        UUID uuid = player.getUniqueId();

        UserConfig config = new UserConfig(player);
        FileConfiguration file = config.getFile();

        final String SECTION = uuid + ".";

        file.set(SECTION + YOGGIES_FRAGMENTS_SECTION, user.getYoggiesFragments());
        file.set(SECTION + DONATION_REMAINDER_SECTION, user.getDonationRemainder());

        ItemStack[] donationRemnants = user.getDonationRemnants();
        final String USER_DONATION_REMNANTS_SECTION = SECTION + DONATION_REMNANTS_SECTION;

        // Remove old values
        file.set(USER_DONATION_REMNANTS_SECTION, null);

        for(int i = 0; i < donationRemnants.length; i++) {

            ItemStack item = donationRemnants[i];

            if(item != null) {
                file.set(USER_DONATION_REMNANTS_SECTION + "." + i, item);
            }
        }

        config.save();

    }

    public static User getUser(Player player) {

        UUID uuid = player.getUniqueId();

        return USERS.containsKey(uuid) ? USERS.get(uuid) : loadUser(player);
    }

}
