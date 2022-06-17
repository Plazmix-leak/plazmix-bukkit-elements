package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class NightCommand extends BaseCommand<Player> {

    public NightCommand() {
        super("night", "ночь");

        setMinimalGroup(Group.MODER);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        player.getWorld().setTime(18_000);
        player.getWorld().setFullTime(18_000);

        plazmixUser.localization().sendMessage(localizationResource -> localizationResource.getMessage("NIGHT_SUCCESS_SET_TIME")
                .replace("%world%", player.getWorld().getName())
                .toText());
    }
}
