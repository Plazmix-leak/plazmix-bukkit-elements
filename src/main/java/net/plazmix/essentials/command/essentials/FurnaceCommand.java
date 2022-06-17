package net.plazmix.essentials.command.essentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class FurnaceCommand extends BaseCommand<Player> {

    public FurnaceCommand() {
        super("furnace", "печка");

        setMinimalGroup(Group.STAR);
    }

    @Override
    protected void onExecute(Player player, String[] strings) {
        player.openInventory(Bukkit.createInventory(null, InventoryType.FURNACE));
    }
}
