package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class ClearCommand extends BaseCommand<Player> {

    public ClearCommand() {
        super("clear");

        setMinimalGroup(Group.JR_MODER);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        player.getInventory().clear();
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));

        player.resetPlayerWeather();
        player.resetPlayerTime();
        player.resetTitle();

        plazmixUser.localization().sendMessage("CLEAR_SUCCESS_RESET");
    }

}
