package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class ExtCommand extends BaseCommand<Player> {

    public ExtCommand() {
        super("ext", "потушить");

        setMinimalGroup(Group.STAR);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        plazmixUser.localization().sendMessage("EXT_MESSAGE");
        player.setFireTicks(0);
    }

}
