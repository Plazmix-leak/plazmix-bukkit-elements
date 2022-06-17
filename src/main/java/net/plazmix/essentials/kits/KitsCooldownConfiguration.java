package net.plazmix.essentials.kits;

import lombok.NonNull;
import net.plazmix.essentials.PlazmixEssentialsPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import net.plazmix.configuration.BaseConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class KitsCooldownConfiguration extends BaseConfiguration {

    public static final long COOLDOWN_MILLIS = TimeUnit.DAYS.toMillis(30);
    public static final KitsCooldownConfiguration INSTANCE = new KitsCooldownConfiguration(PlazmixEssentialsPlugin.getInstance());


    private KitsCooldownConfiguration(@NonNull Plugin plugin) {
        super(plugin, "kits-cooldown.yml");
    }


    private final Map<String, Long> playerCooldownsMap = new HashMap<>();

    @Override
    protected void onInstall(@NonNull FileConfiguration fileConfiguration) {

        for (String playerName : fileConfiguration.getKeys(false)) {
            playerCooldownsMap.put(playerName.toLowerCase(), fileConfiguration.getLong(playerName));
        }
    }

    public void setCooldown(@NonNull Player player) {
        long cooldown = System.currentTimeMillis() + COOLDOWN_MILLIS;

        playerCooldownsMap.put(player.getName().toLowerCase(), cooldown);

        getLoadedConfiguration().set(player.getName(), cooldown);
        saveConfiguration();
    }

    public boolean hasCooldown(@NonNull Player player) {
        Long currentCooldown = playerCooldownsMap.get(player.getName().toLowerCase());

        if (currentCooldown == null) {
            return false;
        }

        return currentCooldown - System.currentTimeMillis() > 0;
    }

    public long getLeftCooldown(@NonNull Player player) {
        Long currentCooldown = playerCooldownsMap.get(player.getName().toLowerCase());

        if (currentCooldown == null) {
            return -1L;
        }

        return currentCooldown - System.currentTimeMillis();
    }

}
