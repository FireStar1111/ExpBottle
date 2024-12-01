package dev.firestar.expbottle.Utils;

import dev.firestar.UpdateUtil;
import dev.firestar.expbottle.Expbottle;
import org.bukkit.Bukkit;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;

public class Update {

    private final Expbottle expbottle;
    private final String VERSION;

    public Update(Expbottle expbottle) {
        this.expbottle = expbottle;
        this.VERSION = expbottle.getDescription().getVersion();
    }
    public File getPluginFile(){
        File pluginFile = new File(expbottle.getDataFolder().getParentFile(), "ExpBottle-" + VERSION + ".jar");
        return pluginFile;
    }
    public boolean checkAndUpdate(){

        return UpdateUtil.checkForUpdates(VERSION, "FireStar1111", "ExpBottle", getPluginFile());

    }

    public void restartServer() {
        getLogger().info("Server wordt herstart om de update toe te passen...");
        Bukkit.getServer().shutdown(); // Stopt de server
    }

}
