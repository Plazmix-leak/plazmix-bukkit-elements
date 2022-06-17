package net.plazmix.essentials.command.essentials;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.essentials.kits.KitRegistry;
import net.plazmix.essentials.kits.KitsCooldownConfiguration;
import net.plazmix.inventory.impl.BasePaginatedInventory;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.NumberUtil;
import net.plazmix.utility.PlayerUtil;

import java.util.Comparator;

public class KitCommand extends BaseCommand<Player> {

    public KitCommand() {
        super("kit", "kits");
    }

    @Override
    protected void onExecute(@NonNull Player player, @NonNull String[] args) {
        new KitInventory().openInventory(player);
    }


    private static class KitInventory extends BasePaginatedInventory {

        public KitInventory() {
            super("Игровые наборы", 5);
        }

        @Override
        public void drawInventory(@NonNull Player player) {

            // структуризация идет нахуй для примерной визуализации расположения китов
            setMarkupSlots(
                    12, 13, 14, 15, 16,
                        22, 23, 24
            );

            Group selfGroup = PlayerUtil.getGroup(player);
            Group maxGroup = KitRegistry.INSTANCE.getGroupKitsMap().keySet().stream().max(Comparator.comparingInt(Group::getLevel)).orElse(null);

            KitRegistry.INSTANCE.getGroupKitsMap().forEach((group, kitCreator) -> {
                Material iconType = Material.BROWN_SHULKER_BOX;

                switch (group) {

                    case DEFAULT: {
                        iconType = Material.SILVER_SHULKER_BOX;
                        break;
                    }

                    case STAR: {
                        iconType = Material.ORANGE_SHULKER_BOX;
                        break;
                    }

                    case COSMO: {
                        iconType = Material.LIME_SHULKER_BOX;
                        break;
                    }

                    case GALAXY: {
                        iconType = Material.YELLOW_SHULKER_BOX;
                        break;
                    }

                    case UNIVERSE: {
                        iconType = Material.MAGENTA_SHULKER_BOX;
                        break;
                    }

                    case LUXURY: {
                        iconType = Material.BLUE_SHULKER_BOX;
                        break;
                    }

                    case SPECIAL: {
                        iconType = Material.RED_SHULKER_BOX;
                        break;
                    }
                }

                addClickItemToMarkup(ItemUtil.newBuilder(iconType)
                                .setName("§eНабор для статуса " + group.getColouredName())
                                .addLore("")
                                .addLore(KitsCooldownConfiguration.INSTANCE.hasCooldown(player) ? ChatColor.RED + NumberUtil.getTime(KitsCooldownConfiguration.INSTANCE.getLeftCooldown(player)) : (selfGroup.getLevel() == group.getLevel() || (group.equals(maxGroup) && selfGroup.getLevel() > maxGroup.getLevel()) ? "§e► Нажмите, чтобы получить!" : "§6► Вы не можете использовать данный набор!"))

                                .build(),

                        (player1, inventoryClickEvent) -> {

                            if (!(selfGroup.getLevel() == group.getLevel() || (group.equals(maxGroup) && selfGroup.getLevel() > maxGroup.getLevel()))) {
                                return;
                            }

                            if (KitsCooldownConfiguration.INSTANCE.hasCooldown(player)) {
                                return;
                            }

                            KitsCooldownConfiguration.INSTANCE.setCooldown(player);
                            kitCreator.set(player);

                            player.closeInventory();

                            player.sendMessage("§d§lPlazmix §8:: §fВы успешно забрали набор статуса " + group.getColouredName());
                        });
            });
        }

    }

}
