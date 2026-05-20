package com.imNikos.flightpotions;

import io.papermc.paper.potion.PotionMix;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;

import java.util.List;

public class FlightItems {

    public static ItemStack FLIGHT_POTION_3;
    public static ItemStack FLIGHT_POTION_8;
    public static NamespacedKey DURATION_KEY;

    public static void init(FlightPotionPlugin plugin) {
        DURATION_KEY = new NamespacedKey(plugin, "flight_duration");

        createPotions(plugin);
        registerRecipes(plugin);
    }

    private static void createPotions(FlightPotionPlugin plugin) {
        // ==========================================
        // 3-MINUTE BASE POTION
        // ==========================================
        FLIGHT_POTION_3 = new ItemStack(Material.POTION);
        PotionMeta meta3 = (PotionMeta) FLIGHT_POTION_3.getItemMeta();
        
        // Custom Name & Lore (Italics removed)
        meta3.displayName(Component.text("Elixir of Flight", NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false));
        meta3.lore(List.of(Component.text("Grants temporary creative flight (3:00)", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)));
        
        // Custom Liquid/Particle Color
        meta3.setColor(Color.fromRGB(255, 215, 0)); 
        
        // 1.21.11 Resource Pack Hook
        meta3.setItemModel(NamespacedKey.fromString("flightpack:elixir_of_flight")); 
        
        // Hidden Duration Tag
        meta3.getPersistentDataContainer().set(DURATION_KEY, PersistentDataType.INTEGER, 3);
        FLIGHT_POTION_3.setItemMeta(meta3);

        // ==========================================
        // 8-MINUTE UPGRADED POTION
        // ==========================================
        FLIGHT_POTION_8 = new ItemStack(Material.POTION);
        PotionMeta meta8 = (PotionMeta) FLIGHT_POTION_8.getItemMeta();
        
        // Custom Name & Lore (Italics removed)
        meta8.displayName(Component.text("Elixir of Flight", NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false));
        meta8.lore(List.of(Component.text("Grants temporary creative flight (8:00)", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)));
        
        // Custom Liquid/Particle Color
        meta8.setColor(Color.fromRGB(255, 215, 0)); 
        
        // 1.21.11 Resource Pack Hook
        meta8.setItemModel(NamespacedKey.fromString("flightpack:elixir_of_flight")); 
        
        // Hidden Duration Tag
        meta8.getPersistentDataContainer().set(DURATION_KEY, PersistentDataType.INTEGER, 8);
        FLIGHT_POTION_8.setItemMeta(meta8);
    }

    private static void registerRecipes(FlightPotionPlugin plugin) {
        // Set up the exact Awkward Potion requirement
        ItemStack awkwardPotion = new ItemStack(Material.POTION);
        PotionMeta awkwardMeta = (PotionMeta) awkwardPotion.getItemMeta();
        awkwardMeta.setBasePotionType(PotionType.AWKWARD);
        awkwardPotion.setItemMeta(awkwardMeta);

        // 1. Awkward Potion + Dried Ghast = 3 Min Flight
        RecipeChoice awkwardChoice = new RecipeChoice.ExactChoice(awkwardPotion);
        RecipeChoice driedGhastChoice = new RecipeChoice.MaterialChoice(Material.DRIED_GHAST); 
        
        NamespacedKey mix3Key = new NamespacedKey(plugin, "mix_flight_3");
        PotionMix flight3Mix = new PotionMix(mix3Key, FLIGHT_POTION_3, awkwardChoice, driedGhastChoice);
        Bukkit.getPotionBrewer().addPotionMix(flight3Mix);

        // 2. 3 Min Flight + Redstone = 8 Min Flight
        RecipeChoice flight3Choice = new RecipeChoice.ExactChoice(FLIGHT_POTION_3);
        RecipeChoice redstoneChoice = new RecipeChoice.MaterialChoice(Material.REDSTONE);
        
        NamespacedKey mix8Key = new NamespacedKey(plugin, "mix_flight_8");
        PotionMix flight8Mix = new PotionMix(mix8Key, FLIGHT_POTION_8, flight3Choice, redstoneChoice);
        Bukkit.getPotionBrewer().addPotionMix(flight8Mix);
    }
}