package dev.firestar.expbottle.Utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class createBottle {

    public static ItemStack create(int exp, Player player) {
        ItemStack bottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = bottle.getItemMeta();
        if (exp >= 242845){
            meta.setDisplayName(color.format("&5Experience bottle " + exp));
        } else {
            meta.setDisplayName(color.format("&aExperience bottle " + exp));
        }
        meta.setLore(Arrays.asList(color.format("&7Made by " + player.getName())));
        NamespacedKey key = new NamespacedKey("expbottle", "xp_amount");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, exp);

        bottle.setItemMeta(meta);
        return bottle;
    }

}
