package shwendel.yoggiessmp.yoggies_jr;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.counter.YoggiesManager;
import shwendel.yoggiessmp.user.User;
import shwendel.yoggiessmp.user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FragmentGUI extends Gui {

    private static int SALVAGEABLE_SLOTS = 36;

    private Player player;
    private User user;

    public FragmentGUI(Player player) {
        super(player, "fragment_gui", "Yoggies Jr.", 6);

        this.player = player;
        this.user = UserManager.getUser(player);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        Inventory inventory = getInventory();

        ItemStack[] donationRemnants = user.getDonationRemnants();

        for(int i = 0; i < donationRemnants.length; i++) {

            ItemStack item = donationRemnants[i];

            if(item != null) {
                addItem(i, item);
            }

        }
        
        Consumer<InventoryClickEvent> cancel = (click -> {

            click.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
            
        });
        
        Icon glass = new Icon(Material.YELLOW_STAINED_GLASS_PANE)
                .setName(" ")
                .onClick(cancel);
        
        for(int i = 36; i < 54; i++) {
            
            addItem(i, glass);
            
        }

        
        addItem(46, new Icon(Material.NAME_TAG)
                .setName(ChatColor.YELLOW + "Yoggies only accepts the finest materials.")
                .setLore(FragmentManager.PROGRESS_LORE)
                .onClick(cancel));

        updateTask(0, 1, update -> {

            List<Integer> discardedSlots = new ArrayList<>();

            int progress = 0;
            int items = 0;

            for(int i = 0; i < SALVAGEABLE_SLOTS; i++) {

                ItemStack item = inventory.getItem(i);

                if(item != null) {

                    int itemProgress = FragmentManager.PROGRESS.getOrDefault(item.getType(), 0);

                    if(itemProgress > 0) {

                        int amount = item.getAmount();

                        discardedSlots.add(i);

                        progress += itemProgress * amount;
                        items += amount;

                    }

                }

            }

            progress += user.getDonationRemainder();

            int fragments = progress / 100;
            int donationRemainder = progress % 100;

            int finalItems = items;

            addItem(49, new Icon(Material.PRISMARINE_SHARD)
                    .setName(ChatColor.YELLOW + "Convert to Yoggies Fragments")
                    .setLore(
                            "",
                            ChatColor.WHITE + "Converting " + ChatColor.AQUA + items + "x Item" + getSuffix(items),
                            ChatColor.DARK_GRAY + "➔ " + ChatColor.YELLOW + fragments + "x Yoggie Fragment" + getSuffix(fragments) + ChatColor.DARK_GRAY + " (+" + donationRemainder + "% towards next)",
                            "",
                            ChatColor.DARK_GRAY + "Left-Click to convert"
                    )
                    .onClick(click -> {

                        click.setCancelled(true);

                        if(finalItems == 0) {

                            player.sendMessage(ChatColor.RED + "There is nothing to convert!");
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);

                            return;

                        }

                        Location location = player.getLocation();

                        // Updated every tick, up to date
                        for(int i : discardedSlots) {
                            inventory.setItem(i, null);
                        }

                        saveInventory(inventory, user);

                        user.setDonationRemainder(donationRemainder);

                        if(fragments > 0) {

                            ItemStack yoggiesFragment = YoggiesManager.getYoggiesFragment();

                            yoggiesFragment.setAmount(fragments);

                            player.getInventory().addItem(yoggiesFragment);
                            user.setYoggiesFragments(user.getYoggiesFragments() + fragments);

                            player.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

                            player.sendMessage("You received " + ChatColor.YELLOW + fragments + "x Yoggies Fragment" + getSuffix(fragments) + ChatColor.WHITE + "!");

                            YoggiesManager.addCounter(fragments);

                        }

                        new BukkitRunnable() {

                            float pitch = 0.5f;

                            @Override
                            public void run() {

                                if(pitch == 1.25) {
                                    cancel();
                                    return;
                                }

                                player.playSound(location, Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 2f, pitch);

                                pitch += 0.25;

                            }

                        }.runTaskTimer(YoggiesSMP.getInstance(), 0, 3);


                    }));

        });

    }

    @Override
    public void onClose(InventoryCloseEvent event) {

        super.onClose(event);

        saveInventory(event.getInventory(), user);

    }

    @Override
    public boolean onClick(InventoryClickEvent event) {
        return true;
    }

    @Override
    public boolean onDrag(InventoryDragEvent event) {
        return true;
    }


    private String getSuffix(int quantity) {
        return quantity == 1 ? "" : "s";
    }

    /**
     * Save the salvage inventory, so they never lose anything
     * @param user The user
     */
    public void saveInventory(Inventory inventory, User user) {

        ItemStack[] items = new ItemStack[SALVAGEABLE_SLOTS];

        for(int i = 0; i < SALVAGEABLE_SLOTS; i++) {

            ItemStack item = inventory.getItem(i);

            if(item != null) {

                items[i] = item;

            }

        }

        user.setDonationRemnants(items);

    }
    
}
