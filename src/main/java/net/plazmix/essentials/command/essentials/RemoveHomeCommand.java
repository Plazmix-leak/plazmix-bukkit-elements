package net.plazmix.essentials.command.essentials;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.essentials.home.PlayerHomeManager;
import net.plazmix.utility.ValidateUtil;

import java.util.Collection;

public class RemoveHomeCommand extends BaseCommand<Player> {

    public RemoveHomeCommand() {
        super("removehome", "remhome", "delhome", "deletehome");
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

        if (args.length == 0) {
            if (homeLocations.size() > 1) {

                player.sendMessage("§cОшибка, у Вас найдено несколько точек дома!");
                player.sendMessage("§cЧтобы удалить определенную, укажите индекс точки");
                return;
            }

            PlayerHomeManager.INSTANCE.removeHome(player.getName(), 0);
            player.sendMessage("§eВаша единственная точка дома была удалена!");
            return;
        }

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

        PlayerHomeManager.INSTANCE.removeHome(player.getName(), homeIndex - 1);
        player.sendMessage("§eТочка дома #" + homeIndex + " была удалена!");
    }

}
