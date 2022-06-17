package net.plazmix.essentials.home;

import com.google.common.collect.ArrayListMultimap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.Location;
import net.plazmix.utility.location.LocationUtil;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerHomeManager {

    public static final PlayerHomeManager INSTANCE = new PlayerHomeManager();

    @Getter
    private final ArrayListMultimap<String, Location> playerHomes = ArrayListMultimap.create();


    public List<Location> getPlayerHomes(@NonNull String playerName) {
        return playerHomes.get(playerName.toLowerCase());
    }

    public void addHome(@NonNull String playerName, @NonNull Location homeLocation) {
        addHome(false, playerName, homeLocation);
    }

    public void addHome(boolean save, @NonNull String playerName, @NonNull Location homeLocation) {
        playerHomes.put(playerName.toLowerCase(), homeLocation);

        if (save) {
            List<String> playerHomes = getPlayerHomes(playerName).stream()
                    .map(LocationUtil::locationToString).collect(Collectors.toList());

            PlayerHomeConfiguration.INSTANCE.getLoadedConfiguration().set("players." + playerName.toLowerCase(), playerHomes);
            PlayerHomeConfiguration.INSTANCE.saveConfiguration();
        }
    }

    public Location getHomeLocation(@NonNull String playerName, int homeIndex) {
        return getPlayerHomes(playerName).get(homeIndex);
    }

    public void removeHome(@NonNull String playerName, int homeIndex) {
        playerHomes.remove(playerName.toLowerCase(), playerHomes.get(playerName.toLowerCase()).get(homeIndex));
    }
}
