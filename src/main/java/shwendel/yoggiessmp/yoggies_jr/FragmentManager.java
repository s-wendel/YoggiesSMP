package shwendel.yoggiessmp.yoggies_jr;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import shwendel.yoggiessmp.YoggiesSMP;
import shwendel.yoggiessmp.util.CaseUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentManager {

    public static final Map<Material, Integer> PROGRESS = new HashMap<>();
    public static final List<String> PROGRESS_LORE = new ArrayList<>();

    private static final String FILE_NAME = "fragments.yml";
    private static final String PROGRESS_SECTION = "progress";

    private static File file;
    private static FileConfiguration fileConfiguration;

    static {
        init();
    }

    public static void init() {

        file = new File(YoggiesSMP.getInstance().getDataFolder().getPath(), FILE_NAME);

        if(!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        fileConfiguration = new YamlConfiguration();

        reload();

    }

    public FileConfiguration getFile() {
        return fileConfiguration;
    }

    public void save() {

        try {

            fileConfiguration.save(file);

        } catch (IOException exception) {

            exception.printStackTrace();

        }

    }

    public static void reload() {

        fileConfiguration = YamlConfiguration.loadConfiguration(file);

        PROGRESS.clear();

        for(String material : fileConfiguration.getConfigurationSection(PROGRESS_SECTION).getKeys(false)) {
            PROGRESS.put(Material.valueOf(material.toUpperCase()), fileConfiguration.getInt(PROGRESS_SECTION + "." + material));
        }

        for(Material material : FragmentManager.PROGRESS.keySet()) {

            String newMaterialName = CaseUtil.toProperCase(material.toString());

            PROGRESS_LORE.add(ChatColor.DARK_GRAY + "➔ " + ChatColor.WHITE + newMaterialName + ChatColor.DARK_GRAY + " " + PROGRESS.get(material) + "% of a Yoggies Fragment");

        }

    }

}
