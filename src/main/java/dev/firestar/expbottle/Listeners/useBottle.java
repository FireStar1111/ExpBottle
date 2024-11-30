package dev.firestar.expbottle.Listeners;

import dev.firestar.expbottle.Expbottle;
import dev.firestar.expbottle.Managers.ExperienceManager;
import dev.firestar.expbottle.Utils.color;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class useBottle implements Listener {

    private final Expbottle expbottle;
    private ExperienceManager experienceManager;
    public useBottle(Expbottle expbottle) {
        this.expbottle = expbottle;
        experienceManager = expbottle.getClassManager().getExperienceManager();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            ItemMeta meta = item.getItemMeta();
            NamespacedKey key = new NamespacedKey("expbottle", "xp_amount");
            PersistentDataContainer container = meta.getPersistentDataContainer();

            if (container.has(key, PersistentDataType.INTEGER)) {

                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    int exp = container.get(key, PersistentDataType.INTEGER);

                    int amount = item.getAmount();
                    int slot = player.getInventory().getHeldItemSlot();
                    if (amount == 1) {
                        player.getInventory().setItem(slot, null);
                    } else {
                        item.setAmount(amount - 1);
                        player.getInventory().setItem(slot, item);
                    }

                    player.getInventory().getHeldItemSlot();


                    event.setCancelled(true);

                    player.giveExp(exp);

                    Location bottleLocation = player.getLocation();
                    player.setRotation(bottleLocation.getYaw(), bottleLocation.getPitch());
                    if (exp >= experienceManager.getExperienceForLevel(250)){
                        player.getWorld().playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                    } else {
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    }
                    player.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, player.getLocation().add(0, 1, 0), 10);

                    player.sendMessage(color.format("&aYou have received &e" + exp + " &aXP from the experience bottle."));
                    System.out.println(player.getName() + " given " + exp + " from experience bottle");
                }
            }
        }
    }

}
