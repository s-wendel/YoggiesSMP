package shwendel.yoggiessmp.bosses.bosses;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import shwendel.yoggiessmp.bosses.Boss;

import java.util.HashMap;
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
    public Material getPortalFrameBlock() {
        return Material.SLIME_BLOCK;
    }

    @Override
    public Material getPortalBlock() {
        return Material.EMERALD_BLOCK;
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

}
