package dev.firestar.expbottle.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class itemStack {
    protected static ItemStack create(String displayname, Material material, int amount, String[] description, boolean hideAtributes, boolean enchanted, boolean hide_enchants,
                            boolean unbreakable, Enchantment enchantment, int enchantmentlvl){

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (hideAtributes){
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if (hide_enchants){
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (enchanted){
            meta.addEnchant(enchantment, enchantmentlvl, true);
        }
        if (unbreakable){
            meta.setUnbreakable(true);
        }
        if (description != null){
            meta.setLore(Arrays.stream(description).toList());
        }

        meta.setDisplayName(color.format(displayname));
        item.setItemMeta(meta);
        return item;
    }

}
