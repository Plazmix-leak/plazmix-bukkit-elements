package net.plazmix.essentials.command.essentials;

import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.coreconnector.core.group.GroupManager;
import net.plazmix.essentials.home.PlayerHomeManager;
import net.plazmix.utility.NumberUtil;

public class SethomeCommand extends BaseCommand<Player> {

    public SethomeCommand() {
        super("sethome", "addhome", "сетдом");
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        Group playerGroup = GroupManager.INSTANCE.getPlayerGroup(player.getName());
        int homeLimit = playerGroup.isStaff() ? (-1) : (playerGroup.ordinal() + 1);

        if (!playerGroup.isStaff() && PlayerHomeManager.INSTANCE.getPlayerHomes(player.getName()).size() >= homeLimit) {
            player.sendMessage("§cОшибка, количество Ваших домов уже привышает лимит в " + NumberUtil.formatting(homeLimit, "точку", "точки", "точек"));
            return;
        }

        PlayerHomeManager.INSTANCE.addHome(true, player.getName(), player.getLocation());
        player.sendMessage("§eНовая точка дома была успешно установлена!");
    }

}
