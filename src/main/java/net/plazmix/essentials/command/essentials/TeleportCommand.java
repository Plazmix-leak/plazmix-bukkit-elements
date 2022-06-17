package net.plazmix.essentials.command.essentials;

import lombok.NonNull;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class TeleportCommand extends BaseCommand<CommandSender> {

    public TeleportCommand() {
        super("teleport", "tp", "тп", "телепорт");
        setMinimalGroup(Group.MODER);
    }

    @Override
    protected void onExecute(CommandSender commandSender, String[] args) {

        if (commandSender instanceof Player) {
            onPlayerExecute(((Player) commandSender), args);

        } else {

            onConsoleExecute(commandSender, args);
        }
    }


    private void onPlayerExecute(@NonNull Player player, @NonNull String... args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);
        if (args.length == 0) {
            plazmixUser.localization().sendMessage("TELEPORT_MESSAGE");
            return;
        }

        // Using - /teleport <player>
        if (args.length < 2) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                plazmixUser.localization().sendMessage("PLAYER_OFFLINE");
                return;
            }

            player.teleport(targetPlayer);
            plazmixUser.localization().sendMessage(localizationResource -> localizationResource.getMessage("TELEPORT_SUCCESS_TELEPORT")
                    .replace("%player%", plazmixUser.getDisplayName())
                    .toText());
            return;
        }

        // Using - /teleport <player from> <player to>
        if (args.length < 3) {

            Player playerFrom = Bukkit.getPlayer(args[0]);
            Player playerTo = Bukkit.getPlayer(args[1]);

            if (playerFrom == null || playerTo == null) {
                plazmixUser.localization().sendMessage("PLAYER_OFFLINE");
                return;
            }

            playerFrom.teleport(playerTo);
            playerFrom.sendMessage("§eВы были телепортированы к " + plazmixUser.getDisplayName());

            player.sendMessage("§aВы телепортировали " + plazmixUser.of(playerFrom).getDisplayName() + " §aк " + plazmixUser.of(playerTo).getDisplayName());
            return;
        }

        // Using - /teleport <x> <y> <z>
        if (args.length < 4) {
            Location locationTo = new Location(player.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));

            player.sendMessage("§aВы были телепортированы на локацию: §f"
                    + player.getWorld().getName() + ", " + locationTo.getX() + ", " + locationTo.getY() + ", " + locationTo.getZ());

            player.teleport(locationTo);
            return;
        }

        // Using - /teleport <player> <x> <y> <z>
        if (args.length < 5) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                plazmixUser.localization().sendMessage("PLAYER_OFFLINE");
                return;
            }

            Location locationTo = new Location(targetPlayer.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));

            targetPlayer.sendMessage("§aВы были телепортированы на локацию: §f"
                    + locationTo.getWorld().getName() + ", " + locationTo.getX() + ", " + locationTo.getY() + ", " + locationTo.getZ());

            player.sendMessage("§aВы телепортировали " + plazmixUser.of(targetPlayer).getDisplayName() + " §aна локацию:"
                    + locationTo.getWorld().getName() + ", " + locationTo.getX() + ", " + locationTo.getY() + ", " + locationTo.getZ());

            targetPlayer.teleport(locationTo);
        }
    }

    private void onConsoleExecute(@NonNull CommandSender commandSender, @NonNull String... args) {
        if (args.length < 2) {
            commandSender.sendMessage("§cTeleport :: Список доступных команд:");

            commandSender.sendMessage("§c- Телепортировать одного игрока к другому - /teleport <кого телепортируем> <к кому телепортируем>");
            commandSender.sendMessage("§c- Телепортация игрока по координатам - /teleport <ник игрока> <x> <y> <z>");
            return;
        }

        // Using - /teleport <player from> <player to>
        if (args.length < 3) {

            Player playerFrom = Bukkit.getPlayer(args[0]);
            Player playerTo = Bukkit.getPlayer(args[1]);

            if (playerFrom == null || playerTo == null) {
                commandSender.sendMessage("§cОшибка, один из указанных Вами игроков не в сети!");
                return;
            }

            playerFrom.teleport(playerTo);
            playerFrom.sendMessage("§eВы были телепортированы к " + PlazmixUser.of(playerTo).getDisplayName());

            commandSender.sendMessage("§aВы телепортировали " + PlazmixUser.of(playerFrom).getDisplayName() + " §aк " + PlazmixUser.of(playerTo).getDisplayName());
            return;
        }

        // Using - /teleport <player> <x> <y> <z>
        if (args.length < 5) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                commandSender.sendMessage("§cОшибка, данный игрок не в сети!");
                return;
            }

            Location locationTo = new Location(targetPlayer.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));

            targetPlayer.sendMessage("§aВы были телепортированы на локацию: §f"
                    + locationTo.getWorld().getName() + ", " + locationTo.getX() + ", " + locationTo.getY() + ", " + locationTo.getZ());

            commandSender.sendMessage("§aВы телепортировали " + PlazmixUser.of(targetPlayer).getDisplayName() + " §aна локацию:"
                    + locationTo.getWorld().getName() + ", " + locationTo.getX() + ", " + locationTo.getY() + ", " + locationTo.getZ());

            targetPlayer.teleport(locationTo);
        }
    }

}
