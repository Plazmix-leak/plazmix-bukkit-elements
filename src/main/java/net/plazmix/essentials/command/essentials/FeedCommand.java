package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class FeedCommand extends BaseCommand<Player> {

    public FeedCommand() {
        super("feed", "food", "еда");

        setMinimalGroup(Group.STAR);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        plazmixUser.localization().sendMessage("FEED_MESSAGE");
        player.setFoodLevel(20);
    }

}
