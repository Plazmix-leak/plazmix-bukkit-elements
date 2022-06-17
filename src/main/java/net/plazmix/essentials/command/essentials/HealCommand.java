package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class HealCommand extends BaseCommand<Player> {

    public HealCommand() {
        super("heal", "health", "жизнь", "здоровье");

        setMinimalGroup(Group.COSMO);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        plazmixUser.localization().sendMessage("HEAL_MESSAGE");

        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
    }

}
