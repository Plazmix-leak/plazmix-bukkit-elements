package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class DayCommand extends BaseCommand<Player> {

    public DayCommand() {
        super("day", "день");

        setMinimalGroup(Group.MODER);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        player.getWorld().setTime(1200);
        player.getWorld().setFullTime(1200);

        plazmixUser.localization().sendMessage(localizationResource -> localizationResource.getMessage("DAY_SUCCESS_SET_TIME")
            .replace("%world%", player.getWorld().getName())
            .toText());
    }

}
