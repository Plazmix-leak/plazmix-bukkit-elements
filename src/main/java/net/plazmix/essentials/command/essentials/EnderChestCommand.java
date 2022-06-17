package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class EnderChestCommand extends BaseCommand<Player> {

    public EnderChestCommand() {
        super("enderchest", "ec", "эндерсундук");

        setMinimalGroup(Group.STAR);
    }

    @Override
    protected void onExecute(Player player, String[] strings) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);
        plazmixUser.handle().openInventory(plazmixUser.handle().getEnderChest());
    }
}