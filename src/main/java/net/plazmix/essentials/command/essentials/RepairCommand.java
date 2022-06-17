package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

public class RepairCommand extends BaseCommand<Player> {

    public RepairCommand() {
        super("repair", "починить");

        setMinimalGroup(Group.COSMO);
    }

    @Override
    protected void onExecute(Player player, String... args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §cОшибка, необходимо держать предмет в руке!");
            return;
        }

        if (!isRepaired(itemInHand)) {
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §cОшибка, этот предмет нельзя починить!");
            return;
        }

        if (plazmixUser.getCoins() < 1000) {
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §cОшибка, у Вас недостаточно средств для покупки!");
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §cВам необходимо ещё " + ChatColor.stripColor(String.valueOf(1000 - plazmixUser.getCoins())) + " монет");
            return;
        }

        plazmixUser.removeCoins(1000);
        itemInHand.setDurability((short) 0);
        plazmixUser.handle().setItemInHand(itemInHand);
        plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно починили этот предмет за §a1000 монет");
    }

    private boolean isRepaired(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        String item = itemStack.getType().name();
        return item.endsWith("SWORD") || item.endsWith("PICKAXE") || item.endsWith("AXE") || item.endsWith("HOE") || item.equals("SHOVEL") || item.endsWith("HELMET") || item.endsWith("CHESTPLATE") || item.endsWith("LEGGINGS") || item.endsWith("BOOTS") || item.equals("BOW") || item.equals("FISHING_ROD") || item.contains("SHEARS") || item.contains("ELYTRA") || item.equals("CARROT_STICK");
    }
}
