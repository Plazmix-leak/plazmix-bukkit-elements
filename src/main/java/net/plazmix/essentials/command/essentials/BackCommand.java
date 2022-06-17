package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.essentials.listener.BackListener;

public class BackCommand extends BaseCommand<Player> {

    public BackCommand() {
        super("back", "backward", "назад");

        setMinimalGroup(Group.COSMO);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        if (!BackListener.backward(player)) {
            plazmixUser.localization().sendMessage("BACKWARD_ERROR_NOT_LOCATION");
        }
    }

}
