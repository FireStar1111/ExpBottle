package dev.firestar.expbottle;

import dev.firestar.expbottle.Managers.ClassManager;
import dev.firestar.expbottle.Utils.DBconnector;
import org.bukkit.plugin.java.JavaPlugin;
import dev.firestar.*;
import java.util.Collections;

public final class Expbottle extends JavaPlugin {

    private ClassManager classManager;


    @Override
    public void onEnable() {
        try {
            DBconnector.connect("firestar3210.nl", 3306, "leuk", "root", Encryptor.decrypt("WJi7OUVCfEX6LGz//uCoTw=="));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Made by Firestar!");
        System.out.println("Plugin inschakelen...");
        classManager = new ClassManager(this);
        classManager.registerManagers();
        classManager.registerCommands();
        classManager.registerListeners();
        classManager.registerUtils();
        System.out.println("Plugin ingeschakeld!");
        if (classManager.getUpdate().checkAndUpdate()){
            classManager.getUpdate().restartServer();
        }

    }

    @Override
    public void onDisable() {
        System.out.println("Plugin uitschakelen...");
        classManager.getUpdate().checkAndUpdate();
        classManager.setPlayers(Collections.emptyList());

        System.out.println("Plugin uitgeschakeld!");
    }

    public ClassManager getClassManager() {
        return classManager;
    }
}
