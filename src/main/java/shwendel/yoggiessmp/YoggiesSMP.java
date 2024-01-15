package shwendel.yoggiessmp;

import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import shwendel.yoggiessmp.bosses.BossTestCommand;
import shwendel.yoggiessmp.counter.YoggiesManager;
import shwendel.yoggiessmp.counter.command.SetShrineCommand;
import shwendel.yoggiessmp.counter.command.SetSpawnCommand;
import shwendel.yoggiessmp.counter.listener.*;
import shwendel.yoggiessmp.enchantments.command.GiveEnchantmentCommand;
import shwendel.yoggiessmp.enchantments.enchantments.africa.AfricaEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.drill.DrillEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.rocket_blast.RocketEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.smelting_touch.SmeltingTouchEnchantmentListener;
import shwendel.yoggiessmp.enchantments.enchantments.speed.SpeedEnchantmentListener;
import shwendel.yoggiessmp.enchantments.listener.EnchantmentAnvilListener;
import shwendel.yoggiessmp.user.UserManager;
import shwendel.yoggiessmp.user.listener.UserQuitListener;
import shwendel.yoggiessmp.yoggies_jr.command.FragmentsCommand;

public final class YoggiesSMP extends JavaPlugin {

    private static YoggiesSMP instance;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerDeathListener(), this);
        //pluginManager.registerEvents(new ShrineDropListener(), this);
        pluginManager.registerEvents(new YoggiesKillListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new EnchantmentAnvilListener(), this);
        pluginManager.registerEvents(new DrillEnchantmentListener(), this);
        pluginManager.registerEvents(new AfricaEnchantmentListener(), this);
        pluginManager.registerEvents(new SpeedEnchantmentListener(), this);
        pluginManager.registerEvents(new RocketEnchantmentListener(), this);
        pluginManager.registerEvents(new UserQuitListener(), this);
        pluginManager.registerEvents(new SmeltingTouchEnchantmentListener(), this);

        getCommand("setshrine").setExecutor(new SetShrineCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        //getCommand("test").setExecutor(new BossTestCommand());
        getCommand("fragments").setExecutor(new FragmentsCommand());
        getCommand("giveenchantment").setExecutor(new GiveEnchantmentCommand());

        new InventoryAPI(this).init();
    }

    @Override
    public void onDisable() {

        for(Player player : Bukkit.getOnlinePlayers()) {
            UserManager.saveUser(player);
        }

        YoggiesManager.save();

        instance = null;

    }

    public static YoggiesSMP getInstance() {
        return instance;
    }

}
