/**
 * Code written by Shiven Dhir
 */
package org.github.s_dhir.ridepearls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.github.s_dhir.ridepearls.commands.RidePearls;
import org.github.s_dhir.ridepearls.commands.RidePearlsTabAutoCompleter;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class RidePearlsPlugin extends JavaPlugin {
    public JavaPluginLoader javaPluginLoader;
    public PluginDescriptionFile pluginDescriptionFile;
    public File dataFolder;
    public File file;

    public boolean isDisabled = false;
    public final Logger logger = Bukkit.getLogger();
    public final Utilities utilityFunctions = new Utilities(this);
    @Nullable public Properties props = null;

    public RidePearlsPlugin() {

        this.javaPluginLoader=null;
        this.pluginDescriptionFile=null;
        this.dataFolder=null;
        this.file=null;
    }
    public RidePearlsPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {

        super(loader, description, dataFolder, file);

        this.javaPluginLoader= loader;
        this.pluginDescriptionFile= description;
        this.dataFolder= dataFolder;
        this.file= file;
    }

    @Override
    public void onEnable() {
        if(isDisabled) return;
        if (true) {
            CompletableFuture<Boolean> loadProps = CompletableFuture.supplyAsync(this::readServerProperties);
            loadProps.thenRun(()-> Bukkit.broadcastMessage(ChatColor.RED + "Error reading server.properties file"));

            getServer().getPluginManager().registerEvents(new PlayerRideEvents(this), this);

            registerCommands();

            this.logger.info("All commands registered!");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin is disabled");
        super.onDisable();
    }


    protected boolean readServerProperties() {
        FileInputStream propsFile;
        try {
            propsFile = new FileInputStream("../server.properties");

            props = new Properties();
            props.load(propsFile);

            return true;
        } catch (Exception ex) {
            try {
                propsFile = new FileInputStream("server.properties");

                props = new Properties();
                props.load(propsFile);

                return true;
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return false;
    }
    public void registerCommands(){
        //this.getCommand("ridepearls").setExecutor(new RidePearls(this));
        //this.getCommand("ridepearls").setTabCompleter(new RidePearlsTabAutoCompleter(this));
    }
}