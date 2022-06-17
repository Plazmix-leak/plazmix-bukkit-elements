package net.plazmix.essentials.auction.inventory;

import lombok.NonNull;
import net.plazmix.essentials.auction.AuctionConfiguration;
import net.plazmix.essentials.auction.AuctionItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.plazmix.inventory.impl.BaseSimpleInventory;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.NumberUtil;

import java.util.Collection;

public class AuctionItemPurchaseInventory extends BaseSimpleInventory {

    private final AuctionItem auctionItem;
    private int currentAmount = 1;

    public AuctionItemPurchaseInventory(@NonNull AuctionItem auctionItem) {
        super("Покупка товара", 5);

        this.auctionItem = auctionItem;
        this.currentAmount = auctionItem.getItemStack().getAmount();
    }

    @Override
    public void drawInventory(Player player) {
        setClickItem(23, ItemUtil.newBuilder(auctionItem.getItemStack())
                        .setAmount(currentAmount)

                        .addLore("")
                        .addLore("§7Текущая количество: §ex" + currentAmount)
                        .addLore("§7Текущая цена: §e" + NumberUtil.formatting(auctionItem.getAmountPrice(currentAmount), "коин", "коина", "коинов"))
                        .addLore("")
                        .addLore("§e▸ Нажмите, чтобы приобрести (x" + currentAmount + ")")

                        .build(),

                (player1, inventoryClickEvent) -> {
                    Collection<AuctionItem> itemsList = AuctionConfiguration.INSTANCE.getAuctionItemCollection();

                    if (!itemsList.contains(auctionItem)) {
                        player.sendMessage("§cОшибка, данного предмета больше нет на аукционе");

                        backward(player);
                        return;
                    }

                    if (auctionItem.isExpired()) {
                        player.sendMessage("§cОшибка, срок данного предмета на аукционе истек!");

                        backward(player);
                        return;
                    }

                    auctionItem.purchase(currentAmount, player);
                    backward(player);
                });

        // plus to item amount
        if (currentAmount + 1 <= auctionItem.getItemStack().getAmount()) {
            setClickItem(20, ItemUtil.newBuilder(Material.STAINED_GLASS_PANE)
                            .setDurability(5)
                            .setName("§a+1")
                            .build(),

                    (player1, inventoryClickEvent) -> {
                        currentAmount++;

                        updateInventory(player);
                    });
        }

        if (currentAmount + 5 <= auctionItem.getItemStack().getAmount()) {
            setClickItem(21, ItemUtil.newBuilder(Material.STAINED_GLASS_PANE)
                            .setDurability(5)
                            .setName("§a+5")
                            .build(),

                    (player1, inventoryClickEvent) -> {
                        currentAmount += 5;

                        updateInventory(player);
                    });
        }

        if (currentAmount + 10 <= auctionItem.getItemStack().getAmount()) {
            setClickItem(22, ItemUtil.newBuilder(Material.STAINED_GLASS_PANE)
                            .setDurability(5)
                            .setName("§a+10")
                            .build(),

                    (player1, inventoryClickEvent) -> {
                        currentAmount += 10;

                        updateInventory(player);
                    });
        }

        // minus to item amount
        if (currentAmount - 1 > 0) {
            setClickItem(26, ItemUtil.newBuilder(Material.STAINED_GLASS_PANE)
                            .setDurability(14)
                            .setName("§c-1")
                            .build(),

                    (player1, inventoryClickEvent) -> {
                        currentAmount--;

                        updateInventory(player);
                    });
        }

        if (currentAmount - 5 > 0) {
            setClickItem(25, ItemUtil.newBuilder(Material.STAINED_GLASS_PANE)
                            .setDurability(14)
                            .setName("§c-5")
                            .build(),

                    (player1, inventoryClickEvent) -> {
                        currentAmount -= 5;

                        updateInventory(player);
                    });
        }

        if (currentAmount - 10 > 0) {
            setClickItem(24, ItemUtil.newBuilder(Material.STAINED_GLASS_PANE)
                            .setDurability(14)
                            .setName("§c-10")
                            .build(),

                    (player1, inventoryClickEvent) -> {
                        currentAmount -= 10;

                        updateInventory(player);
                    });
        }


        setClickItem(41, ItemUtil.newBuilder(Material.SKULL_ITEM)
                        .setDurability(3)
                        .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYyNTkwMmIzODllZDZjMTQ3NTc0ZTQyMmRhOGY4ZjM2MWM4ZWI1N2U3NjMxNjc2YTcyNzc3ZTdiMWQifX19")

                        .setName("§eВернуться назад")
                        .setLore("§7Нажмите, чтобы вернуться назад")
                        .build(),

                (player1, inventoryClickEvent) -> backward(player));
    }

    public void backward(@NonNull Player player) {
        new AuctionItemsInventory().openInventory(player);
    }

}
