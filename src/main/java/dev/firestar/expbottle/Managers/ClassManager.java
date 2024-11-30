package dev.firestar.expbottle.Managers;

import dev.firestar.expbottle.Commands.ExpBottleCommand;
import dev.firestar.expbottle.Expbottle;
import dev.firestar.expbottle.Listeners.onAnvilUse;
import dev.firestar.expbottle.Listeners.useBottle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClassManager {

    private final Expbottle expbottle;
    private ExperienceManager experienceManager;
    public ClassManager(Expbottle expbottle) {
        this.expbottle = expbottle;
    }
    private List<Player> players = new ArrayList<>();

    public void registerCommands(){

        expbottle.getCommand("expbottle").setExecutor(new ExpBottleCommand(expbottle));

    }
    public void registerListeners(){

        expbottle.getServer().getPluginManager().registerEvents(new useBottle(expbottle), expbottle);
        expbottle.getServer().getPluginManager().registerEvents(new onAnvilUse(), expbottle);

    }
    public void registerManagers(){
        experienceManager = new ExperienceManager(expbottle);
    }
    public void registerUtils(){

    }
    public ExperienceManager getExperienceManager() {
        return experienceManager;
    }

    public List<Player> getplayers(){
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
