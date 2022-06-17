package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

import static org.bukkit.Bukkit.getPluginManager;

public class SuicideCommand extends BaseCommand<Player> {

    public SuicideCommand() {
        super("suicide", "умереть", "покурить");

        setMinimalGroup(Group.STAR);
    }


    @Override
    protected void onExecute(Player player, String[] args) {
        EntityDamageEvent suicide = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.SUICIDE, Float.MAX_VALUE);

        PlazmixUser plazmixUser = PlazmixUser.of(player);

        getPluginManager().callEvent(suicide);
        suicide.getEntity().setLastDamageCause(suicide);

        plazmixUser.handle().setHealth(0);
        plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы сдохли! Ахахахах лох");

        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            otherPlayer.sendMessage("§d§lPlazmix §8:: §fВы посмотрите, " + plazmixUser.getDisplayName() + " §fобъебался и сам сдох, §cPRESS F");
        }
    }

}
