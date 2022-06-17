package net.plazmix.essentials.home;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import net.plazmix.configuration.BaseConfiguration;
import net.plazmix.essentials.PlazmixEssentialsPlugin;
import net.plazmix.utility.location.LocationUtil;

public final class PlayerHomeConfiguration extends BaseConfiguration {

    public static final PlayerHomeConfiguration INSTANCE
            = new PlayerHomeConfiguration(PlazmixEssentialsPlugin.getInstance());

    private PlayerHomeConfiguration(@NonNull Plugin plugin) {
        super(plugin, "homes.yml");
    }

    @Override
    protected void onInstall(@NonNull FileConfiguration fileConfiguration) {
        ConfigurationSection playerHomesSection = fileConfiguration.getConfigurationSection("players");

        if (playerHomesSection == null)
            return;

        System.out.println(ChatColor.YELLOW + "[PlazmixSurvival] Loading players homes...");
        for (String playerName : playerHomesSection.getKeys(false))

            for (String homeLocation : playerHomesSection.getStringList(playerName))
                PlayerHomeManager.INSTANCE.addHome(playerName, LocationUtil.stringToLocation(homeLocation));
    }

}
