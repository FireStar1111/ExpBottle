package dev.firestar.expbottle.Managers;

import dev.firestar.expbottle.Expbottle;
import org.bukkit.entity.Player;

public class ExperienceManager {

    private final Expbottle expbottle;

    public ExperienceManager(Expbottle expbottle) {
        this.expbottle = expbottle;
    }


    // Bereken de totale XP voor alle levels van de speler
    public int getTotalExperience(Player player) {
        int level = player.getLevel();
        int expInBar = (int) (player.getExp() * player.getExpToLevel());
        return getExpToLevel(level) + expInBar;
    }

    // Zet het totale XP van de speler exact naar een specifieke waarde
    public void setTotalExperience(Player player, int totalExp) {
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        int level = 0;
        while (getExpToLevel(level + 1) <= totalExp) {
            level++;
        }

        player.setLevel(level);
        int expInBar = totalExp - getExpToLevel(level);

        // Controleer of expInBar binnen de juiste grenzen valt
        if (expInBar < 0) {
            expInBar = 0;
        }
        player.setExp((float) expInBar / player.getExpToLevel());
    }

    // Bereken de ervaring voor een bepaald level
    public int getExpToLevel(int level) {
        if (level <= 16) {
            return level * level + 6 * level;
        } else if (level <= 31) {
            return (int) (2.5 * level * level - 40.5 * level + 360);
        } else {
            return (int) (4.5 * level * level - 162.5 * level + 2220);
        }
    }

    
    // Bereken de totale benodigde ervaring om een bepaald level te bereiken
    public int getExperienceForLevel(int level) {
        return getExpToLevel(level);
    }


}
