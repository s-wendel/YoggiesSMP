package shwendel.yoggiessmp.user;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import shwendel.yoggiessmp.YoggiesSMP;

import java.io.File;
import java.io.IOException;

public class UserConfig {

    private Player player;
    private File file;
    private FileConfiguration fileConfiguration;

    public UserConfig(Player player) {

        this.player = player;
        this.file = new File(YoggiesSMP.getInstance().getDataFolder().getPath() + File.separator + "players", player.getUniqueId() + ".yml");

        if(!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        fileConfiguration = new YamlConfiguration();

        try {

            fileConfiguration.load(file);

        } catch (IOException | InvalidConfigurationException exception) {

            exception.printStackTrace();

        }

    }

    public Player getPlayer() {
        return player;
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

    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

}
