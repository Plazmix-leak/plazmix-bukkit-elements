package net.plazmix.essentials.command.location;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;

public class SpawnCommand extends BaseCommand<Player> {

    public SpawnCommand() {
        super("spawn", "спаун", "спавн");
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        player.teleport(Bukkit.getWorld("Spawn").getSpawnLocation());
    }

}
