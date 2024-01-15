package shwendel.yoggiessmp.bosses.abilities;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.bosses.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpikeAbility extends Ability {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final int HEIGHT = 10;
    private static final int RADIUS = 2; // Square
    private static final int RADIUS_INTERVAL = 2; // Amount of loops between each radius
    private static final int SPEED = 1; // Ticks between intervals
    private static final double HEIGHT_INCREASE = 1; // Height increase of spike per speed tick
    private static final int WARNING_TIMER = 10; // Ticks between warnings
    private static final Material[] WARNING_MATERIALS = new Material[] { // Also used as seconds, 3 materials = 3 seconds
            Material.LIME_CONCRETE,
            Material.YELLOW_CONCRETE,
            Material.RED_CONCRETE
    };
    private static final int DESPAWN_TIMER = 10; // In ticks
    private static final Vector FLING_VECTOR = new Vector(0, 2.25, 0); // Tossed upwards

    private BlockData blockData;

    public SpikeAbility(Material material) {
        this.blockData = material.createBlockData();
    }

    @Override
    public void run(Location location) {

        YoggiesSMP instance = YoggiesSMP.getInstance();

        World world = location.getWorld();
        List<Location> warningLocations = new ArrayList<>();

        for(int x = RADIUS * -1; x <= RADIUS; x++) {

            for(int z = RADIUS * -1; z <= RADIUS; z++) {

                warningLocations.add(location.clone().add(x, 0, z));

            }

        }

        List<BlockDisplay> blocks = new ArrayList<>();

        Location spawnLocation = location.clone().add(0, -1 * HEIGHT, 0);

        for(int y = 0; y < HEIGHT; y++) {

            final double CURRENT_RADIUS = RADIUS * (1 - (double) y / HEIGHT);
            final double INTERVAL = CURRENT_RADIUS / RADIUS_INTERVAL;

            for(double x = CURRENT_RADIUS * -1; x <= CURRENT_RADIUS; x += INTERVAL) {

                for(double z = CURRENT_RADIUS * -1; z <= CURRENT_RADIUS; z += INTERVAL) {

                    BlockDisplay block = (BlockDisplay) world.spawnEntity(spawnLocation.clone().add(
                            x,
                            y,
                            z
                    ), EntityType.BLOCK_DISPLAY);

                    block.setBlock(blockData);

                    blocks.add(block);

                }

            }

        }

        new BukkitRunnable() {

            int counter = 0;

            @Override
            public void run() {

                if(counter == WARNING_MATERIALS.length) {

                    new BukkitRunnable() {

                        int heightCounter = 0;

                        @Override
                        public void run() {

                            if(heightCounter >= HEIGHT) {

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {

                                        for(BlockDisplay block : blocks) {
                                            block.remove();
                                        }

                                    }

                                }.runTaskLater(instance, DESPAWN_TIMER);

                                for(Location warning : warningLocations) {

                                    for(Player player : Bukkit.getOnlinePlayers()) {
                                        player.sendBlockChange(warning, Material.AIR.createBlockData());
                                    }

                                }

                                cancel();
                            }

                            for(BlockDisplay block : blocks) {
                                block.teleport(block.getLocation().clone().add(0, HEIGHT_INCREASE, 0));
                            }

                            world.playSound(location.clone().add(0, HEIGHT_INCREASE, 0), Sound.BLOCK_PISTON_CONTRACT, 2f, 1f);

                            heightCounter += HEIGHT_INCREASE;

                        }

                    }.runTaskTimer(instance, 0, SPEED);

                    for(Entity entity : world.getNearbyEntities(location, RADIUS + 0.5, HEIGHT + 0.5, RADIUS + 0.5)) {

                        if(!(entity instanceof Player)) {
                            continue;
                        }

                        Player player = (Player) entity;

                        player.damage(5);
                        player.setVelocity(FLING_VECTOR);

                    }

                    cancel();
                    return;
                }

                for(Location warning : warningLocations) {

                    for(Player player : Bukkit.getOnlinePlayers()) {
                        player.sendBlockChange(warning, WARNING_MATERIALS[counter].createBlockData());
                    }

                }

                world.playSound(location, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 2f, (float) (0.75f + counter * 0.25));

                counter++;

            }

        }.runTaskTimer(instance, 0, WARNING_TIMER);

    }

}
