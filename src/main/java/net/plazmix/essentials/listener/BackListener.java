package net.plazmix.essentials.listener;

import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;
import java.util.Map;

public final class BackListener
        implements Listener {

    private static final Map<String, Location> BACKWARDS_PLAYERS_LOCATIONS = new HashMap<>();

    public static boolean backward(@NonNull Player player) {
        Location backwardLocation = BACKWARDS_PLAYERS_LOCATIONS.get(player.getName().toLowerCase());

        if (backwardLocation == null) {
            return false;
        }

        player.teleport(backwardLocation);
        return true;
    }


    public void save(@NonNull Player player, @NonNull Location location) {
        BACKWARDS_PLAYERS_LOCATIONS.put(player.getName().toLowerCase(), location);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        save(event.getPlayer(), event.getFrom());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        save(event.getEntity(), event.getEntity().getLocation());
    }

}
