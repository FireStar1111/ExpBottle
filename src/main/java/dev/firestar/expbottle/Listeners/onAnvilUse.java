package dev.firestar.expbottle.Listeners;

import dev.firestar.expbottle.Utils.color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class onAnvilUse implements Listener {

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (player.getOpenInventory().getTopInventory().getType() != InventoryType.ANVIL)
            return;

        Inventory anvilInv = player.getOpenInventory().getTopInventory();

        ItemStack content1 = anvilInv.getItem(0);
        ItemMeta meta = content1.getItemMeta();
        if (meta == null) return;
        if (content1 == null) return;
        NamespacedKey key = new NamespacedKey("expbottle", "xp_amount");
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.INTEGER) || content1.getType().equals(Material.EXPERIENCE_BOTTLE)) {
            if (e.getRawSlot() == 2){
                e.setCancelled(true);
                player.closeInventory();
                player.sendMessage(color.format("&cYou cannot enchant this item!"));
            }

        }
    }
}