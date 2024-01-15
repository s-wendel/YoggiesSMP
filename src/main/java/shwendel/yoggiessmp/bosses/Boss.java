package shwendel.yoggiessmp.bosses;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.EntityType;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Boss {

    protected static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public abstract String getName();
    public abstract double getHealth();
    public abstract EntityType getEntityType();

    /**
     * Returns the frame block of the portal
     * @return The frame block of the portal
     */
    public abstract Material getPortalFrameBlock();
    /**
     * Returns the inner block of the portal (It's small)
     * @return The inner block of the portal (It's small)
     */
    public abstract Material getPortalBlock();

    /**
     * Returns the block used in abilities
     * @return The block used in abilities
     */
    public abstract Material getAbilityBlock();

    public abstract BarColor getBossBarColor();

    /**
     * Required materials necessary to summon a boss
     * @return Key: A material used, Value: The amount of material
     */
    public abstract Map<Material, Integer> getSummoningMaterials();

    /**
     * Runs an ability, each boss can utilize different abilities in different ways
     * @param bossMemory The boss
     */
    public abstract void runAbility(BossMemory bossMemory);

}
