package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.utility.ValidateUtil;

import java.util.Locale;

public class GamemodeCommand extends BaseCommand<Player> {

    private GameMode playerMode;

    public GamemodeCommand(GameMode playerMode) {
        super("<>");
        this.playerMode = playerMode;

        setMinimalGroup(Group.ADMIN);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        // Если режим устанавливается через команду /gamemode <режим>
        if (playerMode == null) {

            /* GM, GAMEMODE */

            if (args.length == 0) {
                player.sendMessage("§d§lPlazmix §8:: §fИспользуйте - §d/gamemode <режим> [игрок]");
                return;
            }

            String gamemodeArgument = (args[0]).toUpperCase(Locale.ROOT);

            if (ValidateUtil.isNumber(gamemodeArgument)) {
                playerMode = GameMode.getByValue(Integer.parseInt(gamemodeArgument));

                if (playerMode == null) {
                    player.sendMessage(ChatColor.RED + "Неверно указан индекс режима: " + gamemodeArgument);

                    return;
                }

            } else {

                try {
                    playerMode = GameMode.valueOf(gamemodeArgument);

                } catch (Exception exception) {

                    player.sendMessage(ChatColor.RED + "Неверно указан тип режима: " + gamemodeArgument);
                    return;
                }
            }

            // Если еще указан ник игрока
            if (args.length > 1) {
                PlazmixUser plazmixTarget = PlazmixUser.of(args[1]);

                if (plazmixTarget.getPlayerId() < 0) {
                    player.sendMessage("§d§lPlazmix §8:: §fДанный игрок ни разу не играл на сервере!");
                    return;
                }

                if (plazmixTarget.handle() == null) {
                    player.sendMessage(ChatColor.RED + "Данный игрок не в сети!");
                    return;
                }

                plazmixTarget.handle().setGameMode(playerMode);

                plazmixTarget.handle().sendMessage("§d§lPlazmix §8:: §fВаш игровой режим был изменен администратором " + plazmixUser.getDisplayName() + " §rна §a" + playerMode);
                plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно изменили игровой режим " + plazmixTarget.getDisplayName() + " §rна §a" + playerMode);
                return;
            }

            plazmixUser.handle().setGameMode(playerMode);
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно изменили свой игровой режим на §a" + playerMode);
        }


        /* GMS, GMC, GMSP, GMA */

        // Если еще указан ник игрока
        if (args.length > 0) {
            PlazmixUser plazmixTarget = PlazmixUser.of(args[0]);

            if (plazmixTarget.getPlayerId() < 0) {
                player.sendMessage("§d§lPlazmix §8:: §fДанный игрок ни разу не играл на сервере!");
                return;
            }

            if (plazmixTarget.handle() == null) {
                player.sendMessage(ChatColor.RED + "Данный игрок не в сети!");
                return;
            }

            plazmixTarget.handle().setGameMode(playerMode);

            plazmixTarget.handle().sendMessage("§d§lPlazmix §8:: §fВаш игровой режим был изменен администратором " + plazmixUser.getDisplayName() + " §rна §a" + playerMode);
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно изменили игровой режим " + plazmixTarget.getDisplayName() + " §rна §a" + playerMode);
            return;
        }

        // Ну и если он не указал ник игрока, то устанавливаем отправителю команды
        plazmixUser.handle().setGameMode(playerMode);
        plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно изменили свой игровой режим на §a" + playerMode);
    }

}

