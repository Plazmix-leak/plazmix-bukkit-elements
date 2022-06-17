package net.plazmix.essentials.command.essentials;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.utility.BukkitPotionUtil;

import java.util.ArrayList;
import java.util.Collection;

public class VanishCommand extends BaseCommand<Player> {

    private final Collection<String> vanishPlayers = new ArrayList<>();

    public VanishCommand() {
        super("vanish", "v", "ваниш", "invis", "invisibility");

        setMinimalGroup(Group.MODER);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        boolean vanishStatus = vanishPlayers.contains(player.getName().toLowerCase());
        setVanish(player, vanishStatus = !vanishStatus);

        player.sendMessage("§d§lPlazmix §8:: §fРежим невидимости: " + (vanishStatus ? "§aвключен" : "§cвыключен"));
    }

    private void setVanish(@NonNull Player player, boolean status) {
        if (status) {
            vanishPlayers.add(player.getName().toLowerCase());

            player.addPotionEffect(BukkitPotionUtil.getInfinityPotion(PotionEffectType.INVISIBILITY));

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                otherPlayer.showPlayer(player);
            }
        }

        else {
            vanishPlayers.remove(player.getName().toLowerCase());

            player.removePotionEffect(PotionEffectType.INVISIBILITY);

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                otherPlayer.hidePlayer(player);
            }
        }
    }

}
