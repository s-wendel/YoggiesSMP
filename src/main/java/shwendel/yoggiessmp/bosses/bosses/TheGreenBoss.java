package shwendel.yoggiessmp.bosses.bosses;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.bosses.Boss;
import shwendel.yoggiessmp.bosses.BossMemory;
import shwendel.yoggiessmp.bosses.abilities.ShockwaveAbility;
import shwendel.yoggiessmp.bosses.abilities.SpikeAbility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheGreenBoss extends Boss {

    @Override
    public String getName() {
        return "The Green";
    }

    @Override
    public double getHealth() {
        return 2500;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.SLIME;
    }

    @Override
    public Material getPortalFrameBlock() {
        return Material.SLIME_BLOCK;
    }

    @Override
    public Material getPortalBlock() {
        return Material.EMERALD_BLOCK;
    }

    @Override
    public Material getAbilityBlock() {
        return Material.SLIME_BLOCK;
    }

    @Override
    public BarColor getBossBarColor() {
        return BarColor.GREEN;
    }

    @Override
    public Map<Material, Integer> getSummoningMaterials() {

        Map<Material, Integer> materials = new HashMap<>();

        materials.put(Material.SLIME_BLOCK, 50);
        materials.put(Material.LIME_SHULKER_BOX, 25);
        materials.put(Material.EMERALD_BLOCK, 256);
        materials.put(Material.LIME_STAINED_GLASS, 1024);

        return materials;
    }

    @Override
    public void runAbility(BossMemory bossMemory) {

        YoggiesSMP instance = YoggiesSMP.getInstance();

        LivingEntity bossEntity = bossMemory.getEntity();
        World world = bossEntity.getWorld();
        byte stage = bossMemory.getStage();

        List<Player> players = bossMemory.getParticipatingPlayers();

        if(stage <= 2) {

            // Calm ?
            int number = random.nextInt(2);

            switch(number) {

                case 0:

                    for(Player player : players) {

                        Location location = player.getLocation();

                        location.setY(world.getHighestBlockYAt(location));

                        new SpikeAbility(getAbilityBlock()).run(location);

                    }

                    break;

                case 1:

                    bossEntity.setVelocity(new Vector(0, 1.5, 0));

                    new BukkitRunnable() {

                        @Override
                        public void run() {

                            if(bossEntity.isOnGround()) {

                                new ShockwaveAbility(getAbilityBlock()).run(bossEntity.getLocation().clone().add(0, -0.5, 0));

                                cancel();

                            }

                        }

                    }.runTaskTimer(instance, 5, 5);

                    break;


            }

        } else if(stage <= 4) {

            // Mild


        } else {

            // Oh it's over


        }

    }

}
