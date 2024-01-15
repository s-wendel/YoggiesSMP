package shwendel.yoggiessmp.bosses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import shwendel.yoggiessmp.YoggiesSMP;

import java.util.ArrayList;
import java.util.List;

public class BossMemory {

    private static final int STAGES = 5;

    private LivingEntity entity;
    private Boss boss;

    public BossMemory(Boss boss) {
        this.boss = boss;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public Boss getBoss() {
        return boss;
    }

    /**
     * Returns a stage, 1-5. 1 is base 5 is final
     * @return
     */
    public byte getStage() {
        return (byte) (STAGES - (entity.getHealth() / (entity.getMaxHealth() / STAGES)));
    }

    public List<Player> getParticipatingPlayers() {
        return entity.getWorld().getPlayers();
    }

    public void spawn(Location location) {

        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, boss.getEntityType());

        this.entity.setInvulnerable(true);

        BossMemory bossMemory = this;

        new BukkitRunnable() {

            int counter = 0;

            @Override
            public void run() {

                if(entity.isDead()) {

                    cancel();
                    return;

                }

                int stage = getStage();

                if(counter == STAGES - stage) {

                    counter = 0;

                    boss.runAbility(bossMemory);

                }

                counter++;

            }

        }.runTaskTimer(YoggiesSMP.getInstance(), 0, 20);

    }

}
