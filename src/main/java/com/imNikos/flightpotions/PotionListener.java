package com.imNikos.flightpotions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionListener implements Listener {

    private final FlightPotionPlugin plugin;

    public PotionListener(FlightPotionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) return;

        if (item.getItemMeta().getPersistentDataContainer().has(FlightItems.DURATION_KEY, PersistentDataType.INTEGER)) {
            Player player = event.getPlayer();
            
            int minutes = item.getItemMeta().getPersistentDataContainer().get(FlightItems.DURATION_KEY, PersistentDataType.INTEGER);
            long durationMillis = (long) minutes * 60 * 1000;
            long expireTime = System.currentTimeMillis() + durationMillis;
            
            // Save expiration time
            player.getPersistentDataContainer().set(plugin.getFlightTimeKey(), PersistentDataType.LONG, expireTime);
            
            // Grant flight
            player.setAllowFlight(true);
            
            // Apply harmless Luck effect so it shows in the inventory menu (duration is in ticks, 20 ticks = 1 sec)
            int durationTicks = minutes * 60 * 20;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, durationTicks, 0, false, false, true));
            
            player.sendMessage(Component.text("Flight granted for " + minutes + " minutes!", NamedTextColor.GREEN));
        }
    }

    // When the player logs off, PAUSE the timer
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getPersistentDataContainer().has(plugin.getFlightTimeKey(), PersistentDataType.LONG)) {
            long expireTime = player.getPersistentDataContainer().get(plugin.getFlightTimeKey(), PersistentDataType.LONG);
            long timeLeft = expireTime - System.currentTimeMillis();
            
            // Save the remaining time and delete the active timer
            if (timeLeft > 0) {
                player.getPersistentDataContainer().set(plugin.getPausedTimeKey(), PersistentDataType.LONG, timeLeft);
            }
            player.getPersistentDataContainer().remove(plugin.getFlightTimeKey());
        }
    }

    // When the player logs back in, RESUME the timer
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getPersistentDataContainer().has(plugin.getPausedTimeKey(), PersistentDataType.LONG)) {
            long timeLeft = player.getPersistentDataContainer().get(plugin.getPausedTimeKey(), PersistentDataType.LONG);
            long newExpireTime = System.currentTimeMillis() + timeLeft;
            
            // Restore active timer and delete the paused memory
            player.getPersistentDataContainer().set(plugin.getFlightTimeKey(), PersistentDataType.LONG, newExpireTime);
            player.getPersistentDataContainer().remove(plugin.getPausedTimeKey());
            
            player.setAllowFlight(true);
        }
    }
}