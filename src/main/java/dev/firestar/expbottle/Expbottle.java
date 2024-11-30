package dev.firestar.expbottle;

import dev.firestar.expbottle.Managers.ClassManager;
import dev.firestar.expbottle.Utils.DBconnector;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public final class Expbottle extends JavaPlugin {

    private ClassManager classManager;


    @Override
    public void onEnable() {
        DBconnector.connect("firestar3210.nl", 3306, "leuk", "root", "JTM@1611");
        System.out.println("Made by Firestar!");
        System.out.println("Plugin inschakelen...");
        classManager = new ClassManager(this);
        classManager.registerManagers();
        classManager.registerCommands();
        classManager.registerListeners();
        classManager.registerUtils();
        System.out.println("Plugin ingeschakeld!");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin uitschakelen...");
        classManager.setPlayers(Collections.emptyList());

        System.out.println("Plugin uitgeschakeld!");
    }

    public ClassManager getClassManager() {
        return classManager;
    }
}
