package net.plazmix.essentials.auction.inventory;

import net.plazmix.essentials.auction.AuctionConfiguration;
import net.plazmix.essentials.auction.AuctionItem;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.plazmix.inventory.impl.BasePaginatedInventory;
import net.plazmix.utility.DateUtil;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.NumberUtil;

public class AuctionExpiredStorageInventory extends BasePaginatedInventory {

    public AuctionExpiredStorageInventory() {
        super("Хранилище товаров", 5);
    }

    @Override
    public void drawInventory(Player player) {
        addRowToMarkup(2, 1);
        addRowToMarkup(3, 1);
        addRowToMarkup(4, 1);

        PlazmixUser plazmixUser = PlazmixUser.of(player);

        setOriginalItem(5, ItemUtil.newBuilder(Material.SIGN)
                .setName("§6Общая информация")
                .addLore("§7Всего предметов: §f" + AuctionConfiguration.INSTANCE.getExpiredPlayerItems(player.getName()).size())
                .build());

        for (AuctionItem auctionItem : AuctionConfiguration.INSTANCE.getExpiredPlayerItems(player.getName())) {
            int buyPrice = ((int) Math.round(auctionItem.getItemPrice() * 0.25));

            addClickItemToMarkup(ItemUtil.newBuilder(auctionItem.getItemStack())

                            .addLore("")
                            .addLore("§7Цена выкупа: §e" + NumberUtil.formattingSpaced(auctionItem.getItemPrice(), "коин", "коина", "коинов") + "  §8(-75%)")
                            .addLore("§7Истек: §f" + DateUtil.formatTime(auctionItem.getExpireTime(), DateUtil.DEFAULT_DATETIME_PATTERN))

                            .addLore("")
                            .addLore((plazmixUser.getCoins() < buyPrice) ? "§cУ Вас недостаточно монет для выкупа!" : "§e▸ Нажмите, чтобы выкупить!")

                            .build(),

                    (player1, inventoryClickEvent) -> {

                        if (plazmixUser.getCoins() < buyPrice) {
                            return;
                        }

                        auctionItem.purchase(auctionItem.getItemStack().getAmount(), player);
                        updateInventory(player);
                    });
        }

        if (AuctionConfiguration.INSTANCE.getExpiredPlayerItems(player.getName()).isEmpty()) {

            setOriginalItem(23, ItemUtil.newBuilder(Material.GLASS_BOTTLE)
                    .setName("§cНичего не найдено!")
                    .build());
        }

        setClickItem(41, ItemUtil.newBuilder(Material.SKULL_ITEM)
                        .setDurability(3)
                        .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYyNTkwMmIzODllZDZjMTQ3NTc0ZTQyMmRhOGY4ZjM2MWM4ZWI1N2U3NjMxNjc2YTcyNzc3ZTdiMWQifX19")

                        .setName("§eВернуться назад")
                        .setLore("§7Нажмите, чтобы вернуться назад")
                        .build(),

                (player1, inventoryClickEvent) -> new AuctionItemsInventory().openInventory(player));
    }

}
