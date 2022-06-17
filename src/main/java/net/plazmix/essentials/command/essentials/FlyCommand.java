package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class FlyCommand extends BaseCommand<Player> {

    public FlyCommand() {
        super("fly", "flying", "полет");

        setMinimalGroup(Group.ADMIN);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        boolean flying = !player.getAllowFlight();
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        plazmixUser.localization().sendMessage(localizationResource -> localizationResource.getMessage("FLY_MESSAGE")
                .replace("%fly%", (flying ? "§aвключено" : "§cвыключено"))
                .toText());

        player.setAllowFlight(flying);
    }

}
