package net.plazmix.essentials.region;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import net.plazmix.configuration.BaseConfiguration;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.essentials.PlazmixEssentialsPlugin;
import net.plazmix.utility.JsonUtil;
import net.plazmix.utility.PlayerUtil;
import net.plazmix.utility.location.LocationUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public final class RegionConfiguration extends BaseConfiguration {

    public static final RegionConfiguration INSTANCE
            = new RegionConfiguration(PlazmixEssentialsPlugin.getInstance());

    @Getter
    private final Collection<RegionSecurity> regionSecurityCollection
            = new ArrayList<>();


    public RegionConfiguration(@NonNull Plugin plugin) {
        super(plugin, "regions.yml");
    }

    @Override
    protected void onInstall(@NonNull FileConfiguration fileConfiguration) {
        ConfigurationSection configurationSection = fileConfiguration.getConfigurationSection("Regions");

        if (configurationSection == null) {
            return;
        }

        for (String regionName : configurationSection.getKeys(false)) {
            RegionSecurity regionSecurity = RegionSecurity.parseSection( configurationSection.getConfigurationSection(regionName) );

            // Add region.
            regionSecurityCollection.add(regionSecurity);
        }
    }

    public Collection<RegionSecurity> getOwnRegions(@NonNull Player player) {
        return regionSecurityCollection.stream().filter(regionSecurity -> regionSecurity.getOwner().equalsIgnoreCase(player.getName()))
                .collect(Collectors.toList());
    }

    public Collection<RegionSecurity> getPlayerRegions(@NonNull Player player) {
        return regionSecurityCollection.stream().filter(regionSecurity -> regionSecurity.canBuild(player.getName()))
                .collect(Collectors.toList());
    }

    public RegionSecurity getRegionSecurity(@NonNull String regionName) {
        return regionSecurityCollection.stream().filter(regionSecurity -> regionSecurity.getName().equalsIgnoreCase(regionName))
                .findFirst()
                .orElse(null);
    }

    public void saveRegionSecurity(@NonNull RegionSecurity regionSecurity) {
        ConfigurationSection configurationSection = getLoadedConfiguration().createSection("Regions." + regionSecurity.getName());
        configurationSection.set("Owner", regionSecurity.getOwner());

        configurationSection.set("Cuboid.Center", LocationUtil.locationToString(regionSecurity.getCenterLocation()));
        configurationSection.set("Cuboid.Pos1", LocationUtil.locationToString(regionSecurity.getCuboidRegion().getStart()));
        configurationSection.set("Cuboid.Pos2", LocationUtil.locationToString(regionSecurity.getCuboidRegion().getEnd()));

        configurationSection.set("Members", regionSecurity.getMemberCollection());

        configurationSection.set("NumberCache", JsonUtil.toJson(regionSecurity.getNumberCacheMap()));

        configurationSection.set("Flags", JsonUtil.toJson(regionSecurity.getFlags()));
        configurationSection.set("TempFlags", JsonUtil.toJson(regionSecurity.getTempFlags()));

        saveConfiguration();
    }

    public void removeRegionSecurity(@NonNull RegionSecurity regionSecurity) {
        getLoadedConfiguration().set("Regions." + regionSecurity.getName(), null);
        saveConfiguration();
    }


    public int getPlayerRegionLimit(@NonNull Player player) {
        Group playerGroup = PlayerUtil.getGroup(player);

        return (playerGroup.isAdmin() ? Integer.MAX_VALUE : playerGroup.ordinal() + 1);
    }

}
