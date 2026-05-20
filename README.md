# Fly Potion | Elixir of Flight 
![Image of the elixir of flight](https://cdn.modrinth.com/data/cached_images/1dea107e71460f38f67a988ff3fbdc8fbe93b8cd.png)

**Elixir of Flight** is a premium, survival-integrated plugin that introduces a balanced, immersive way to achieve temporary creative-style flight. Designed for servers that want to reward exploration and end-game progression, this plugin provides players with the ability to traverse the world from above without granting permanent operator permissions or disruptive bypass commands.

**DOWNLOAD THE RESOURCE PACK FOR THE PLUGIN TO FUNCTION PROPERLY!**
***
### Plugin made by imNikos.
 
  
Follow my socials:

https://www.instagram.com/imnikos_/

https://www.youtube.com/@imnikoss

https://discord.gg/9xJgzNqEaJ

***

## Key Features

* **Balanced Progression:** Flight is handled through a consumable, magical item. Perfect for rewarding players in custom crates, quest rewards, or high-tier brewing recipes.
  
* **Immersive Crafting:** Fully compatible with the Minecraft brewing system. Players can craft the `Elixir of Flight` using a custom recipe (Awkward Potion + Dried Ghast), providing a natural path for players to earn their flight.
  
* **Action Bar Countdown:** Never worry about falling from the sky unexpectedly. A clean, non-intrusive Action Bar timer tracks your remaining flight time, turning red for an intense 10-second countdown before your flight duration expires.
  
* **Logout-Safe Timers:** Using persistent data storage, the plugin tracks your remaining flight time even when you log off. Your timer pauses securely the moment you disconnect and resumes exactly where you left off when you return.
  
* **Custom Texture Support:** The Elixir is not just another water bottle. The plugin supports custom model data, allowing for unique, high-definition icons for your potions that match your server's custom aesthetic.
  
* **Survival-Friendly:** No permanent permissions are required. The flight is granted via temporary potion effects, keeping the integrity of your survival world intact while allowing for incredible building and exploration capabilities.

## How It Works

When a player consumes an **Elixir of Flight**, they are granted creative-style flight for the duration of the potion. An inventory icon (Luck effect) provides a visual status indicator, while the Action Bar keeps the player informed of their time remaining. When the timer hits zero—or the player reaches the ground—flight is gracefully disabled.


## How To Brew

The **Elixir of Flight** has a very simple brewing recipe. Using an awkward potion, add a **dried ghast** to the brewing stand, and watch as your potion of flight begins to brew! You can extend the duration of the flight effect up to 8 minutes with **redstone dust**.

![Potion of flight being brewed](https://cdn.modrinth.com/data/cached_images/182f1168bbd432cb60c185092d29f8c1dc0f3c03.png)
![Potion of flight being brewed to 8 mins](https://cdn.modrinth.com/data/cached_images/38a80fb581e7039cd63ca6d81f6a49a393cf7da0.png)

## Download & Installation

### 1. Install the Plugin

* Navigate to the **Releases** tab on the right side of this GitHub repository.
* Download the latest `FlightPotion.jar`.
* Drop the `.jar` file into your server's `plugins/` folder.

### 2. Setup the Resource Pack

* From the same **Releases** tab, download `FlightPack.zip`.
* Upload the `.zip` file to a hosting service like **[mc-packs.net](https://mc-packs.net/)**.
* Copy the Direct Download URL and SHA-1 Hash provided by the site.
* Open your server's `server.properties` file and paste them into the appropriate fields:
```properties
resource-pack=YOUR_DIRECT_URL_HERE
resource-pack-sha1=YOUR_SHA1_HASH_HERE
require-resource-pack=true

```
---

*Developed by Nikos for high-performance Paper/Spigot servers.*



* Save the file and **restart your server**.
