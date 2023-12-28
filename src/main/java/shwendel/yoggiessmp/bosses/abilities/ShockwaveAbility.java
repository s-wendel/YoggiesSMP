package shwendel.yoggiessmp.bosses.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Vector2d;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.bosses.Ability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.PI;

public class ShockwaveAbility extends Ability {

    private static final double MAX_DEGREES = 360;
    private static final int BLOCKS = 90; // Amount of blocks in the shockwave
    private static final int RADIUS = 15; // How far it goes
    private static final double INTERVALS = 0.375; // How much the current radius is incremented by
    private static final int SPEED = 1; // In ticks, how fast an interval is updated
    private static final double DAMAGE = 2.5;
    private static final Vector FLING_VECTOR = new Vector(0, 1, 0); // Tossed upwards

    // The angles, Key: An angle e.g. 9, Value: A 2d Vector containing the X and Y values for the specified angle
    private static Map<Integer, Vector2d> angles = new HashMap<>();

    // I forgot interpolations existed!!!!!!!!!!!!
    static {

        for(int i = 0; i < MAX_DEGREES; i += MAX_DEGREES / BLOCKS) {

            angles.put(i, new Vector2d(
                    Math.cos(i * PI / (double) 180),
                    Math.sin(i * PI / (double) 180)
            ));

        }

    }

    private BlockData blockData;

    public ShockwaveAbility(Material material) {
        this.blockData = material.createBlockData();
    }

    @Override
    public void run(Location location) {

        World world = location.getWorld();

        Map<Integer, BlockDisplay> blocks = new HashMap<>();

        for(int i = 0; i < MAX_DEGREES; i += MAX_DEGREES / BLOCKS) {

            BlockDisplay block = (BlockDisplay) world.spawnEntity(location, EntityType.BLOCK_DISPLAY);

            block.setBlock(blockData);

            blocks.put(i, block);

        }

        world.playSound(location, Sound.BLOCK_ANVIL_LAND, 2f, 0.5f);

        new BukkitRunnable() {

            double currentRadius = 0;

            @Override
            public void run() {

                if(currentRadius >= RADIUS) {

                    for(BlockDisplay block : blocks.values()) {
                        block.remove();
                    }

                    cancel();
                    return;

                }

                for(Map.Entry<Integer, BlockDisplay> entry : blocks.entrySet()) {

                    int angle = entry.getKey();
                    BlockDisplay block = entry.getValue();

                    Vector2d vector = angles.get(angle);

                    block.teleport(location.clone().add(
                            vector.x * currentRadius,
                            0,
                            vector.y * currentRadius
                    ));

                    for(Entity entity : block.getNearbyEntities(1, 1, 1)) {

                        if(!(entity instanceof Player)) {
                            continue;
                        }

                        Player player = (Player) entity;

                        if(player.isOnGround()) {

                            player.damage(DAMAGE);
                            player.setVelocity(FLING_VECTOR);

                        }

                    }

                }

                currentRadius += INTERVALS;

            }

        }.runTaskTimer(YoggiesSMP.getInstance(), 0, SPEED);

    }

}
