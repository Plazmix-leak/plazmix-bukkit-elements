package net.plazmix.essentials.command.essentials;

import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;
import org.bukkit.entity.Player;

public class GodCommand extends BaseCommand<Player> {

    public GodCommand() {
        super("god", "бог");

        setMinimalGroup(Group.ADMIN);
    }

    @Override
    protected void onExecute(Player player, String[] strings) {

        boolean god = player.getNoDamageTicks() <= 1;

        player.setNoDamageTicks(god ? Integer.MAX_VALUE : 1);
        player.sendMessage("§d§lPlazmix §8:: §fРежим невидимости: " + (god ? "§aвключен" : "§cвыключен"));
    }
}
