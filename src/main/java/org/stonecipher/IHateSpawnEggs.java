package org.stonecipher;

import org.bukkit.plugin.java.JavaPlugin;
import org.stonecipher.event.SpawnEggUseEvent;

public class IHateSpawnEggs  extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SpawnEggUseEvent(), this);
    }
}
