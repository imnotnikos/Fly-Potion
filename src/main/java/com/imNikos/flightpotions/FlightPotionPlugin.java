package com.imNikos.flightpotions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class FlightPotionPlugin extends JavaPlugin {

    private NamespacedKey flightTimeKey;
    private NamespacedKey pausedTimeKey;

    @Override
    public void onEnable() {
        this.flightTimeKey = new NamespacedKey(this, "flight_expire_time");
        this.pausedTimeKey = new NamespacedKey(this, "flight_paused_time");

        FlightItems.init(this);
        getServer().getPluginManager().registerEvents(new PotionListener(this), this);

        startFlightTimer();
        
        getLogger().info("FlightPotions with actionbars enabled!");
    }

    private void startFlightTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getPersistentDataContainer().has(flightTimeKey, PersistentDataType.LONG)) {
                        long expireTime = player.getPersistentDataContainer().get(flightTimeKey, PersistentDataType.LONG);
                        long timeLeft = expireTime - now;
                        
                        // 1. Time is Up!
                        if (timeLeft <= 0) {
                            player.getPersistentDataContainer().remove(flightTimeKey);
                            player.removePotionEffect(PotionEffectType.LUCK); // Strip the dummy inventory icon
                            
                            if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                                player.setAllowFlight(false);
                                player.setFlying(false);
                                player.sendMessage(Component.text("Your flight potion has worn off!", NamedTextColor.RED, TextDecoration.BOLD));
                            }
                        } 
                        // 2. Final 10 Seconds (Intense Countdown)
                        else if (timeLeft <= 10000) {
                            int seconds = (int) (timeLeft / 1000) + 1;
                            player.sendActionBar(Component.text("Flight Ends In: " + seconds + "s!", NamedTextColor.RED, TextDecoration.BOLD));
                        } 
                        // 3. Normal Timer Display
                        else {
                            long totalSeconds = timeLeft / 1000;
                            long mins = totalSeconds / 60;
                            long secs = totalSeconds % 60;
                            String timeString = String.format("%d:%02d", mins, secs);
                            
                            player.sendActionBar(Component.text("Flight Time: ", NamedTextColor.GRAY)
                                    .append(Component.text(timeString, NamedTextColor.GOLD)));
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 5L); // Runs faster (every 5 ticks / 0.25s) for a smoother countdown visual
    }

    public NamespacedKey getFlightTimeKey() { return flightTimeKey; }
    public NamespacedKey getPausedTimeKey() { return pausedTimeKey; }
}