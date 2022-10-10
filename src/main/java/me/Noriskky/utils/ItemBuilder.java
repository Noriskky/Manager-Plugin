package me.Noriskky.utils;


import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {


    private ItemStack item;

    public ItemBuilder(final Material material, final int amount) {
        this.item = new ItemStack(material, amount);
    }

    public ItemBuilder(final Material material, final int amount, final int data) {
        this.item = new ItemStack(material, amount, (short)data);
    }

    public ItemBuilder(final ItemStack item) {
        this.item = item;
    }

    public ItemBuilder setData(final int data) {
        this.item.setDurability((short)data);
        return this;
    }

    public ItemBuilder setMaterial(final Material m) {
        this.item.setType(m);
        return this;
    }

    public ItemBuilder setAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }
    public ItemBuilder setName(final String name) {
        final ItemMeta m = this.item.getItemMeta();
        m.setDisplayName(name);
        this.item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setLore(final String... lore) {
        final ItemMeta m = this.item.getItemMeta();
        m.setLore((List) Arrays.asList(lore));
        this.item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setUnbreakable() {
        final ItemMeta m = this.item.getItemMeta();
        m.setUnbreakable(true);
        this.item.setItemMeta(m);
        return this;
    }

    public ItemBuilder enchant(final Enchantment ench, final int lvl) {
        this.item.addUnsafeEnchantment(ench, lvl);
        return this;
    }

    public ItemBuilder addFlags(final ItemFlag... flag) {
        final ItemMeta m = this.item.getItemMeta();
        m.addItemFlags(flag);
        this.item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setLeatherColor(final Color color) {
        final LeatherArmorMeta m = (LeatherArmorMeta)this.item.getItemMeta();
        m.setColor(color);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setSkullOwner(final String owner) {
        final SkullMeta m = (SkullMeta)this.item.getItemMeta();
        m.setOwner(owner);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setPotionType(final PotionEffectType type) {
        final PotionMeta m = (PotionMeta)this.item.getItemMeta();
        m.setMainEffect(type);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setBookAuthor(final String author) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setAuthor(author);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setBookContent(final String... pages) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setPages(pages);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setBookTitle(final String title) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setTitle(title);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setBookMeta(final String title, final String author, final String... pages) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setTitle(title);
        m.setAuthor(author);
        m.setPages(pages);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }

    public ItemBuilder setEggType(final EntityType type) {
        if (this.item != null && this.item.getType() == Material.LEGACY_MONSTER_EGG && type != null && type.getName() != null) {
            try {
                final String version = Bukkit.getServer().getClass().toString().split("\\.")[3];
                final Class<?> craftItemStack = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
                final Object nmsItemStack = craftItemStack.getDeclaredMethod("asNMSCopy", ItemStack.class).invoke(null, this.item);
                final Object nbtTagCompound = Class.forName("net.minecraft.server." + version + ".NBTTagCompound").newInstance();
                final Field nbtTagCompoundField = nmsItemStack.getClass().getDeclaredField("tag");
                nbtTagCompoundField.setAccessible(true);
                nbtTagCompound.getClass().getMethod("setString", String.class, String.class).invoke(nbtTagCompound, "id", type.getName());
                nbtTagCompound.getClass().getMethod("set", String.class, Class.forName("net.minecraft.server." + version + ".NBTBase")).invoke(nbtTagCompoundField.get(nmsItemStack), "EntityTag", nbtTagCompound);
                this.item = (ItemStack)craftItemStack.getDeclaredMethod("asCraftMirror", nmsItemStack.getClass()).invoke(null, nmsItemStack);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}
