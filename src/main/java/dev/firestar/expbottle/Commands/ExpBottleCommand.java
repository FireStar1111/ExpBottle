package dev.firestar.expbottle.Commands;

import dev.firestar.expbottle.Expbottle;
import dev.firestar.expbottle.Managers.ExperienceManager;
import dev.firestar.expbottle.Utils.color;
import dev.firestar.expbottle.Utils.createBottle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExpBottleCommand implements CommandExecutor {

    private final Expbottle expbottle;
    private final ExperienceManager experienceManager;
    private final String ADMIN_PERMISSION = "expbottle.force";
    private final int MIN_XP = 10;
    private final int MIN_LEVEL = 2;
    private final int MAX_LEVEL = 99999;

    public ExpBottleCommand(Expbottle expbottle) {
        this.expbottle = expbottle;
        this.experienceManager = expbottle.getClassManager().getExperienceManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dit commando kan alleen door een speler worden gebruikt.");
            return true;
        }

        Player player = (Player) sender;
        int totalExp = experienceManager.getTotalExperience(player);
        boolean force = args.length > 1 && args[1].equalsIgnoreCase("force");

        if (args.length < 1) {
            player.sendMessage(color.format("&cUse: /expbottle <amount exp or level (example: 30l) or 'all'>"));
            return true;
        }

        try {
            // Verwerk een specifiek XP-hoeveelheid
            int exp = parseExpArgument(args[0], player, totalExp, force);
            if (exp < 0) return true;

            ItemStack bottle = createBottle.create(exp, player);
            player.getInventory().addItem(bottle);

            if (!force || !player.hasPermission(ADMIN_PERMISSION)) {
                experienceManager.setTotalExperience(player, totalExp - exp);
            }

        } catch (NumberFormatException e) {
            player.sendMessage(color.format("&cInvalid input! Use <number>l, 'all', or a specific XP amount."));
        }

        return true;
    }

    private int parseExpArgument(String arg, Player player, int totalExp, boolean force) {
        if (arg.equalsIgnoreCase("all")) {
            if (!validateXp(totalExp, player, force)) return -1;
            return totalExp;
        } else if (arg.toLowerCase().contains("l")) {
            int level = Integer.parseInt(arg.toLowerCase().replace("l", ""));
            if (!validateLevel(level, player, force)) return -1;
            return experienceManager.getExperienceForLevel(level);
        } else {
            int exp = Integer.parseInt(arg);
            if (!validateXp(exp, player, force)) return -1;
            return exp;
        }
    }

    private boolean validateXp(int xp, Player player, boolean force) {
        if (xp < MIN_XP) {
            if (!force || !player.hasPermission(ADMIN_PERMISSION)) {
                player.sendMessage(color.format("&cYou can't make bottles with less than " + MIN_XP + " XP!"));
                return false;
            }
        }
        if (experienceManager.getTotalExperience(player) < xp) {
            if (!force || !player.hasPermission(ADMIN_PERMISSION)) {
                player.sendMessage(color.format("&cYou don't have enough XP to make this bottle!"));
                return false;
            }
        }
        return true;
    }

    private boolean validateLevel(int level, Player player, boolean force) {
        if (level < MIN_LEVEL || level > MAX_LEVEL) {
            if (!force || !player.hasPermission(ADMIN_PERMISSION)) {
                player.sendMessage(color.format("&cLevel must be between " + MIN_LEVEL + " and " + MAX_LEVEL + "!"));
                return false;
            }
        }
        if (player.getLevel() < level) {
            if (!force || !player.hasPermission(ADMIN_PERMISSION)) {
                player.sendMessage(color.format("&cYou don't have enough levels to make this bottle!"));
                return false;
            }
        }
        return true;
    }
}
