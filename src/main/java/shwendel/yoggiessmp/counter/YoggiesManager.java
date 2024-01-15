package shwendel.yoggiessmp.counter;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import shwendel.yoggiessmp.YoggiesSMP;

import java.util.HashMap;
import java.util.Map;

public class YoggiesManager {

    public static final Material YOGGIES_FRAGMENT_MATERIAL = Material.PRISMARINE_SHARD;
    public static final String YOGGIES_FRAGMENT_NAME = ChatColor.YELLOW + "Yoggies Fragment";
    public static final int SHRINE_RANGE = 2;

    public static int counter;
    public static int stage;
    public static Location spawnLocation;
    public static Location shrineLocation;
    public static BossBar bossBar;

    private static final Map<Integer, String> INTERVALS = new HashMap<>();
    private static final YoggiesSMP INSTANCE = YoggiesSMP.getInstance();
    private static FileConfiguration config = INSTANCE.getConfig();

    private static final String COUNTER_SECTION = "counter";
    private static final String STAGE_SECTION = "stage";
    private static final String SPAWN_SECTION = "spawn";
    private static final String SHRINE_SECTION = "shrine";
    private static final int FIREWORKS_RANGE = 50;
    private static final int FIREWORK_INTERVALS = 10;


    static {

        // Counter intervals
        INTERVALS.put(-500, "Yoggies Fragments take 1.5x the materials.");
        INTERVALS.put(-250, "The Yoggies has disabled Rocket Blast.");
        INTERVALS.put(-125, "The Yoggies has disabled Smelting Touch.");
        INTERVALS.put(-50, "The Yoggies has disabled Drill.");
        INTERVALS.put(0, "Yoggies Fragments take 1x the materials.");
        INTERVALS.put(50, "Yoggies Fragments take 0.9x the materials.");
        INTERVALS.put(125, "Every sunrise, Yoggies blesses the lands with a gift.");
        INTERVALS.put(250, "Yoggies has improved his gifts.");
        INTERVALS.put(500, "Yoggies Fragments take 0.75x the materials.");

        counter = config.getInt(COUNTER_SECTION);
        stage = config.getInt(STAGE_SECTION);
        bossBar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        spawnLocation = config.getLocation(SPAWN_SECTION, null);
        shrineLocation = config.getLocation(SHRINE_SECTION, null);

        updateBossBar();

    }

    public static void save() {

        config.set(COUNTER_SECTION, counter);
        config.set(STAGE_SECTION, stage);
        config.set(SPAWN_SECTION, spawnLocation);
        config.set(SHRINE_SECTION, shrineLocation);

        INSTANCE.saveConfig();

    }

    private static void updateBossBar() {

        /**String color = "";
        String emotion = "";

        if(counter >= MAX_COUNTER * 2 / 3) {
            color = "GREEN";
            emotion = "☺";
        } else if(counter >= MAX_COUNTER * 1 / 3) {
            color = "YELLOW";
            emotion = "☹";
        } else {
            color = "RED";
            emotion = "=/";
        }

        ChatColor chatColor = ChatColor.valueOf(color);
        BarColor barColor = BarColor.valueOf(color);

        bossBar.setTitle(chatColor + "Counter ➔ " + counter + " " + emotion);
        bossBar.setColor(barColor);

        double progress = (double) counter / MAX_COUNTER;

        if(progress <= 0) {

            progress = 0;

        } else if(progress >= 1) {

            progress = 1;

        }

        bossBar.setProgress(progress);*/

    }

    private static void playSound(Sound sound, float volume, float pitch) {

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, volume, pitch);
        }

    }

    public static void addCounter(int amount) {

        if(amount == 0) {
            return;
        }

        counter += amount;

        playSound(Sound.ITEM_GOAT_HORN_SOUND_1, 1.5f, 0.5f);
        updateBossBar();

    }

    public static void removeCounter(int amount) {

        if(amount == 0) {
            return;
        }

        counter -= amount;

        playSound(Sound.ENTITY_RAVAGER_AMBIENT, 1.5f, 1f);
        updateBossBar();

    }

    public static void addStage() {

        stage++;

        playSound(Sound.ENTITY_RAVAGER_CELEBRATE, 1f, 1f);

        updateBossBar();

        World world = spawnLocation.getWorld();

        new BukkitRunnable() {

            int fireworkCounter = 0;

            @Override
            public void run() {

                if(fireworkCounter == FIREWORK_INTERVALS) {
                    cancel();
                    return;
                }

                for(int x = -1 * FIREWORKS_RANGE; x <= FIREWORKS_RANGE; x += 5) {

                    for(int z = -1 * FIREWORKS_RANGE; z <= FIREWORKS_RANGE; z += 5) {

                        Firework firework = (Firework) world.spawnEntity(spawnLocation.clone().add(x, 50, z), EntityType.FIREWORK);

                        FireworkMeta fireworkMeta = firework.getFireworkMeta();

                        fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.GREEN).build());

                        firework.setFireworkMeta(fireworkMeta);

                    }

                }

                fireworkCounter++;

            }

        }.runTaskTimer(YoggiesSMP.getInstance(), 0, 20);

        Location platform = spawnLocation.clone().add(0, 75, 0);

        for(int x = -2; x <= 2; x++) {

            for(int z = -2; z <= 2; z++) {

                platform.clone().add(x, 0, z).getBlock().setType(Material.CHERRY_PLANKS);

            }

        }

        Block block = platform.add(0, 1, 0).getBlock();

        block.setType(Material.CHEST);

        Chest chest = (Chest) block.getState();
        Location chestLocation = chest.getLocation();

        Inventory inventory = chest.getInventory();

        for(int i = 0; i < 27; i++) {

            ItemStack item;

            final ItemStack DEFAULT = new ItemStack(Material.COPPER_BLOCK);

            final ItemStack MAIN = getYoggiesFragment();

            switch(i) {

                // Side Reward
                case 11: case 12: case 13: case 14: case 15:

                    item = MAIN;

                    break;

                default:

                    item = DEFAULT;
                    break;


            }

            inventory.setItem(i, item);

        }

        Bukkit.broadcastMessage("A bundle has dropped at " + ChatColor.GREEN + chestLocation.getBlockX() + ", " + chestLocation.getBlockY() + ", " + chestLocation.getBlockZ());


    }

    public static boolean inShrineRange(Location location) {
        return Math.abs(location.getBlockX()) - shrineLocation.getBlockX() <= SHRINE_RANGE && Math.abs(location.getBlockZ()) - shrineLocation.getBlockZ() <= SHRINE_RANGE;
    }

    public static ItemStack getYoggiesFragment() {

        ItemStack item = new ItemStack(YOGGIES_FRAGMENT_MATERIAL);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(YOGGIES_FRAGMENT_NAME);

        item.setItemMeta(itemMeta);

        return item;
    }

}
