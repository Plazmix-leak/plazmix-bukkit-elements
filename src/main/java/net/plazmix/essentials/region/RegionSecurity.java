package net.plazmix.essentials.region;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.plazmix.utility.location.region.CuboidRegion;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import net.plazmix.utility.JsonUtil;
import net.plazmix.utility.location.LocationUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RegionSecurity {

    @SuppressWarnings("unchecked")
    public static RegionSecurity parseSection(@NonNull ConfigurationSection configurationSection) {
        String regionName = configurationSection.getName();
        String ownerName = configurationSection.getString("Owner");

        Location centerLocation = LocationUtil.stringToLocation( configurationSection.getString("Cuboid.Center") );
        Location position1 = LocationUtil.stringToLocation( configurationSection.getString("Cuboid.Pos1") );
        Location position2 = LocationUtil.stringToLocation( configurationSection.getString("Cuboid.Pos2") );

        CuboidRegion cuboidRegion = new CuboidRegion(position1, position2);

        Collection<String> memberCollection = configurationSection.getStringList("Members");

        Map<String, Integer> numberCache = JsonUtil.fromJson(configurationSection.getString("NumberCache"), Map.class);

        Map<String, Boolean> flags = JsonUtil.fromJson(configurationSection.getString("Flags"), Map.class);
        Map<String, Long> tempFlags = JsonUtil.fromJson(configurationSection.getString("TempFlags"), Map.class);

        return new RegionSecurity(regionName, ownerName, centerLocation, cuboidRegion, memberCollection, numberCache, flags, tempFlags);
    }

    public static RegionSecurity create(@NonNull Player player, @NonNull Location centerLocation, @NonNull CuboidRegion cuboidRegion) {
        String regionName = player.getName().concat("-") + (RegionConfiguration.INSTANCE.getOwnRegions(player).size() + 1);

        RegionSecurity regionSecurity = new RegionSecurity(regionName, player.getName(), centerLocation, cuboidRegion,
                new ArrayList<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());

        regionSecurity.save();
        return regionSecurity;
    }

    public static RegionSecurity find(@NonNull String regionName) {
        return RegionConfiguration.INSTANCE.getRegionSecurity(regionName);
    }

    public static Collection<RegionSecurity> find(@NonNull Player player) {
        return RegionConfiguration.INSTANCE.getPlayerRegions(player);
    }


    private final String name;
    private final String owner;

    private final Location centerLocation;
    private final CuboidRegion cuboidRegion;

    private final Collection<String> memberCollection;

    private final Map<String, Integer> numberCacheMap;

    private final Map<String, Boolean> flags;
    private final Map<String, Long> tempFlags;


    public int getNumberCacheMap(@NonNull String data) {
        return numberCacheMap.getOrDefault(data.toLowerCase(), 0);
    }

    public void setNumberCacheMap(@NonNull String data, int value) {
        numberCacheMap.put(data.toLowerCase(), value);
    }

    public void addNumberCache(@NonNull String data, int value) {
        numberCacheMap.put(data.toLowerCase(), getNumberCacheMap(data) + value);
    }

    public void incrementNumberCache(@NonNull String data) {
        addNumberCache(data, 1);
    }

    public void decrementNumberCache(@NonNull String data) {
        addNumberCache(data, -1);
    }

    public void removeNumberCache(@NonNull String data) {
        numberCacheMap.remove(data.toLowerCase());
    }

    public boolean hasFlag(@NonNull String flagName) {
        return flags.getOrDefault(flagName.toLowerCase(), false);
    }

    public void setFlag(@NonNull String flagName, boolean flag) {
        flags.put(flagName.toLowerCase(), flag);
    }

    public boolean hasTempFlag(@NonNull String flagName) {
        long flagExpireTime = tempFlags.getOrDefault(flagName.toLowerCase(), -1L);

        if (!(flagExpireTime > 0 && flagExpireTime - System.currentTimeMillis() > 0)) {
            removeTempFlag(flagName);
        }

        return flagExpireTime > 0 && flagExpireTime - System.currentTimeMillis() > 0;
    }

    public void addTempFlag(@NonNull String flagName, long expireTimeMillis) {
        tempFlags.put(flagName.toLowerCase(), expireTimeMillis);
    }

    public void removeTempFlag(@NonNull String flagName) {
        tempFlags.remove(flagName.toLowerCase());
    }


    public boolean hasMember(@NonNull String memberName) {
        return memberCollection.contains(memberName.toLowerCase());
    }

    public void addMember(@NonNull String memberName) {
        memberCollection.add(memberName.toLowerCase());
    }

    public void removeMember(@NonNull String memberName) {
        memberCollection.remove(memberName.toLowerCase());
    }


    public boolean contains(@NonNull Location location) {
        return cuboidRegion != null && cuboidRegion.contains(location);
    }

    public boolean contains(@NonNull Block block) {
        return cuboidRegion != null && cuboidRegion.contains(block);
    }

    public boolean canBuild(@NonNull String playerName) {
        return owner.equalsIgnoreCase(playerName) || hasMember(playerName);
    }


    public void save() {
        RegionConfiguration.INSTANCE.saveRegionSecurity(this);
    }

    public void delete() {
        RegionConfiguration.INSTANCE.removeRegionSecurity(this);
    }

}
