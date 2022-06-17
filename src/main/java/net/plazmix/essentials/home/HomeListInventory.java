package net.plazmix.essentials.home;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.coreconnector.core.group.GroupManager;
import net.plazmix.inventory.impl.BasePaginatedInventory;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.NumberUtil;
import net.plazmix.utility.location.LocationUtil;

public class HomeListInventory extends BasePaginatedInventory {

    public HomeListInventory() {
        super("Список домов", 5);
    }

    @Override
    public void drawInventory(Player player) {
        addRowToMarkup(2, 1);
        addRowToMarkup(3, 1);
        addRowToMarkup(4, 1);

        setOriginalItem(5, ItemUtil.newBuilder(Material.SIGN)
                .setName("§6Общая информация")

                .addLore("§7Всего домов: §a" + PlayerHomeManager.INSTANCE.getPlayerHomes(player.getName()).size() + (getHomesLimit(player) <= 0 ? "" : "§f/§c" + getHomesLimit(player)))
                .addLore("§7Средняя дистация до всех точек: §f" + NumberUtil.formatting((int) getMiddleHomesDistances(player), "блок", "блока", "блоков"))
                .build());

        int homeCounter = 0;
        for (Location location : PlayerHomeManager.INSTANCE.getPlayerHomes(player.getName())) {
            homeCounter++;

            ItemUtil.ItemBuilder itemBuilder = ItemUtil.newBuilder(Material.BED);
            itemBuilder.setName(ChatColor.GREEN + "#" + homeCounter);

            itemBuilder.addLore("");
            itemBuilder.addLore("§7Локация:");
            itemBuilder.addLore(" §f" + LocationUtil.locationToString(location));
            itemBuilder.addLore("");

            if (player.getWorld().equals(location.getWorld())) {
                itemBuilder.addLore("§7Дистанция до точки: " + NumberUtil.formatting((int) player.getLocation().distance(location), "блок", "блока", "блоков"));

            } else {

                itemBuilder.addLore("§cВы находитесь в разных мирах!");
            }

            itemBuilder.addLore("");
            itemBuilder.addLore("§a▸ Нажмите, чтобы телепортироваться!");


            addClickItemToMarkup(itemBuilder.build(), (player1, inventoryClickEvent) -> player.teleport(location));
        }

        if (homeCounter <= 0) {
            setOriginalItem(23, ItemUtil.newBuilder(Material.GLASS_BOTTLE)
                    .setName("§cПусто")

                    .addLore("§7На данный момент у вас нет точек дома!")
                    .addLore("§7Вы можете их создать, встав на определенную")
                    .addLore("§7локацию, а затем введя команду §e/sethome")
                    .build());
        }
    }

    private double getMiddleHomesDistances(@NonNull Player player) {
        double count = 0;
        double sum = 0;

        for (Location location : PlayerHomeManager.INSTANCE.getPlayerHomes(player.getName()))
            if (location.getWorld().equals(player.getWorld())) {

                sum += location.distance(player.getLocation());
                count++;
            }

        return Math.floor(sum / count);
    }

    private int getHomesLimit(@NonNull Player player) {
        Group playerGroup = GroupManager.INSTANCE.getPlayerGroup(player.getName());
        return playerGroup.isStaff() ? (-1) : (playerGroup.ordinal() + 1);
    }
}
