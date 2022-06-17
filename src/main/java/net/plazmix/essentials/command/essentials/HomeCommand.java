package net.plazmix.essentials.command.essentials;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.essentials.home.HomeListInventory;
import net.plazmix.essentials.home.PlayerHomeManager;
import net.plazmix.utility.NumberUtil;
import net.plazmix.utility.ValidateUtil;
import net.plazmix.utility.cooldown.PlayerCooldownUtil;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class HomeCommand extends BaseCommand<Player> {

    public HomeCommand() {
        super("home", "house", "дом", "домой");
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        Collection<Location> homeLocations = PlayerHomeManager.INSTANCE.getPlayerHomes(player.getName());

        if (homeLocations.isEmpty()) {
            player.sendMessage("§cОшибка, у Вас не установлена точка дома!");

            player.sendMessage(" ");

            player.sendMessage("§cДля того, чтобы установить, Вам необходимо встать на");
            player.sendMessage("§cнеобходимую локацию и написать /sethome");
            return;
        }

        if (PlayerCooldownUtil.hasCooldown("player_home", player)) {

            player.sendMessage("§cДля повторного использования данной команды подождите еще "
                    + NumberUtil.getTime( PlayerCooldownUtil.getCooldown("player_home", player) ));
            return;
        }

        if (args.length == 0) {

            if (homeLocations.size() > 1) {
                new HomeListInventory().openInventory(player);

                return;
            }

            Location homeLocation = PlayerHomeManager.INSTANCE.getHomeLocation(player.getName(), 0);
            player.teleport(homeLocation);

            return;
        }

        // Usage - /home list
        if (args[0].equalsIgnoreCase("list")) {
            new HomeListInventory().openInventory(player);

            return;
        }

        // Usage - /home <index of home point>
        if (!ValidateUtil.isNumber(args[0])) {
            player.sendMessage("§cОшибка, указанный Вами аргумент не является числом!");

            return;
        }

        int homeIndex = Integer.parseInt(args[0]);

        if (homeIndex <= 0) {
            player.sendMessage("§cОшибка, индекс точки дома не может быть отрицательным или равен нулю!");
            return;
        }

        if (homeIndex > PlayerHomeManager.INSTANCE.getPlayerHomes(player.getName()).size()) {
            player.sendMessage("§cОшибка, индекс точки дома превышает общее количество Ваших домов!");
            return;
        }

        Location homeLocation = PlayerHomeManager.INSTANCE.getHomeLocation(player.getName(), (homeIndex - 1));
        player.teleport(homeLocation);

        PlayerCooldownUtil.putCooldown("player_home", player, TimeUnit.SECONDS.toMillis(30));
    }

}
