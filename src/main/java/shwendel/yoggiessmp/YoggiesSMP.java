package shwendel.yoggiessmp;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shwendel.yoggiessmp.bosses.BossTestCommand;
import shwendel.yoggiessmp.counter.YoggiesManager;
import shwendel.yoggiessmp.counter.command.SetShrineCommand;
import shwendel.yoggiessmp.counter.command.SetSpawnCommand;
import shwendel.yoggiessmp.counter.listener.*;
import shwendel.yoggiessmp.enchantments.enchantments.africa.AfricaEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.drill.DrillEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.rocket_blast.RocketEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.speed.SpeedEnchantmentListener;
import shwendel.yoggiessmp.enchantments.listener.EnchantmentAnvilListener;

public final class YoggiesSMP extends JavaPlugin {

    private static YoggiesSMP instance;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new ShrineDropListener(), this);
        pluginManager.registerEvents(new YoggiesChatListener(), this);
        pluginManager.registerEvents(new YoggiesKillListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new EnchantmentAnvilListener(), this);
        pluginManager.registerEvents(new DrillEnchantmentListener(), this);
        pluginManager.registerEvents(new AfricaEnchantmentListener(), this);
        pluginManager.registerEvents(new SpeedEnchantmentListener(), this);
        pluginManager.registerEvents(new RocketEnchantmentListener(), this);

        getCommand("setshrine").setExecutor(new SetShrineCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("test").setExecutor(new BossTestCommand());

        instance = this;
    }

    @Override
    public void onDisable() {

        YoggiesManager.save();

        instance = null;

    }

    public static YoggiesSMP getInstance() {
        return instance;
    }

}
