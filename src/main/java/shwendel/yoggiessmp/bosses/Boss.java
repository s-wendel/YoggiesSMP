package shwendel.yoggiessmp.bosses;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;

import java.util.Map;

public abstract class Boss {

    public abstract String getName();
    public abstract double getHealth();

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

    public abstract BarColor getBossBarColor();

    /**
     * Required materials necessary to summon a boss
     * @return Key: A material used, Value: The amount of material
     */
    public abstract Map<Material, Integer> getSummoningMaterials();

}
