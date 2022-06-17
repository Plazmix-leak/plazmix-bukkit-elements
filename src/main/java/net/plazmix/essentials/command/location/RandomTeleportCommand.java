package net.plazmix.essentials.command.location;

import net.plazmix.essentials.PlazmixEssentialsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.utility.NumberUtil;
import net.plazmix.utility.location.LocationUtil;

public class RandomTeleportCommand extends BaseCommand<Player> {

    public RandomTeleportCommand() {
        super("rtp", "tpr");
    }

    @Override
    protected void onExecute(Player player, String[] args) {

        if (player.getWorld().getName().contains("nether") || player.getWorld().getName().contains("the_end")) {
            player.sendMessage("§d§lPlazmix §8:: §cОшибка, в Вашем мире рандомная телепортация отключена!");
            return;
        }

        Bukkit.getScheduler().runTask(PlazmixEssentialsPlugin.getInstance(), () -> {
            Location randomLocation = getRandomLocation(30_000, Bukkit.getWorld("world"));

            player.teleport(randomLocation);
            player.sendMessage("§d§lPlazmix §8:: §fВы были перемещены на новую локацию: §e" + LocationUtil.locationToString(randomLocation));

            // Refresh player chunks.
            player.getWorld().regenerateChunk(
                    player.getLocation().getChunk().getX(), player.getLocation().getChunk().getZ()
            );
        });
    }

    public Location getRandomLocation(int distance, World world) {
        double x = NumberUtil.randomDouble(-distance, distance);
        double z = NumberUtil.randomDouble(-distance, distance);

        double y = world.getHighestBlockYAt((int)x, (int)z);

        Location randomLocation = new Location(world, x, y, z);

        if (randomLocation.getBlock().isLiquid() || randomLocation.getBlock().getRelative(BlockFace.DOWN).isLiquid()) {
            return getRandomLocation(distance, world);
        }

        return randomLocation;
    }

}
