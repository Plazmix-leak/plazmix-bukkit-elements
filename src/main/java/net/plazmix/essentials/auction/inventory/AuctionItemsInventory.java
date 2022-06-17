package net.plazmix.essentials.auction.inventory;

import net.plazmix.essentials.auction.function.AuctionItemSort;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import net.plazmix.essentials.PlazmixEssentialsPlugin;
import net.plazmix.inventory.impl.BasePaginatedInventory;
import net.plazmix.essentials.auction.AuctionConfiguration;
import net.plazmix.essentials.auction.AuctionItem;
import net.plazmix.essentials.auction.function.AuctionItemFilter;
import net.plazmix.utility.DateUtil;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.NumberUtil;

import java.util.stream.Collectors;

public class AuctionItemsInventory extends BasePaginatedInventory {

    private AuctionItemSort auctionItemSort = AuctionItemSort.UNKNOWN;
    private AuctionItemFilter auctionItemFilter = AuctionItemFilter.OF_DAY;

    public AuctionItemsInventory() {
        super("Аукцион", 5);
    }

    @Override
    public void drawInventory(Player player) {
        addRowToMarkup(2, 1);
        addRowToMarkup(3, 1);
        addRowToMarkup(4, 1);

        setOriginalItem(5, ItemUtil.newBuilder(Material.SIGN)
                .setName("§6Общая информация")

                .addLore("§7Всего товаров: §f" + AuctionConfiguration.INSTANCE.getAuctionItemCollection().size())
                .addLore("§7Выставленные Вами: §f" + AuctionConfiguration.INSTANCE.getPlayerItems(player.getName()).size())
                .build());

        int itemCounter = 0;
        for (AuctionItem auctionItem : AuctionConfiguration.INSTANCE.getAuctionItemCollection()
                .stream()

                .sorted(auctionItemSort.getItemComparator())
                .filter(auctionItemFilter.getItemFilter())

                .collect(Collectors.toList())) {


            addClickItemToMarkup(ItemUtil.newBuilder(auctionItem.getItemStack())
                            .addLore("")
                            .addLore("§7Выставил: " + PlazmixUser.of(auctionItem.getItemOwner()).getDisplayName())
                            .addLore("§7Цена: §e" + NumberUtil.formattingSpaced(auctionItem.getItemPrice(), "коин", "коина", "коинов"))

                            .addLore("")
                            .addLore("§7Истекает: §f" + DateUtil.formatTime(auctionItem.getExpireTime(), DateUtil.DEFAULT_DATETIME_PATTERN))
                            .addLore("")

                            .addLore(auctionItem.getItemOwner().equalsIgnoreCase(player.getName()) ? "§6▸ Данный товар выставлен Вами!" : "§a▸ Нажмите, чтобы перейти к покупке!")

                            .build(),
                    (player1, inventoryClickEvent) -> {

                        if (auctionItem.getItemOwner().equalsIgnoreCase(player.getName())) {
                            player.sendMessage("§cОшибка, Вы не можете приобретать свой товар!");
                            return;
                        }

                        new AuctionItemPurchaseInventory(auctionItem).openInventory(player);
                    });

            itemCounter++;
        }

        if (itemCounter <= 0) {
            setOriginalItem(23, ItemUtil.newBuilder(Material.GLASS_BOTTLE)
                    .setName("§cНичего не найдено!")
                    .build());
        }

        setClickItem(38, ItemUtil.newBuilder(Material.SKULL_ITEM)
                        .setDurability(3)

                        .setName("§aСортировка товаров")
                        .addLore("")
                        .addLore("§7При помощи данной функции можно")
                        .addLore("§7отсортировать просмотр товаров с")
                        .addLore("§7аукциона")

                        .addLore("")
                        .addLore("§7Текущая сортировка: §f" + auctionItemSort.getSortingName())
                        .addLore("")

                        .addLore("§a▸ Нажмите ЛКМ, чтобы сменить сортировку на следующую")
                        .addLore("§a▸ Нажмите ПКМ, чтобы сменить сортировку на предыдущую")

                        .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTViZTIyYjVkNGE4NzVkNzdkZjNmNzcxMGZmNDU3OGVmMjc5MzlhOTY4NGNiZGIzZDMxZDk3M2YxNjY4NDkifX19")
                        .build(),

                (player1, inventoryClickEvent) -> {

                    if (inventoryClickEvent.isLeftClick()) {
                        auctionItemSort = auctionItemSort.next();
                    } else if (inventoryClickEvent.isRightClick()) {
                        auctionItemSort = auctionItemSort.back();
                    }

                    updateInventory(player);
                });

        setClickItem(39, ItemUtil.newBuilder(Material.SKULL_ITEM)
                        .setDurability(3)

                        .setName("§aФильтр товаров")
                        .addLore("")
                        .addLore("§7При помощи данной функции можно")
                        .addLore("§7отфильтровать просмотр товаров с")
                        .addLore("§7аукциона")

                        .addLore("")
                        .addLore("§7Текущий фильтр: §f" + auctionItemFilter.getSortingName())
                        .addLore("")

                        .addLore("§a▸ Нажмите ЛКМ, чтобы сменить фильтр на следующий")
                        .addLore("§a▸ Нажмите ПКМ, чтобы сменить фильтр на предыдущий")

                        .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRiZjhkODBhZTlkODdiYzI0OGI4OGRhNmFkNDdkZjQ2YmVmMjRiNmVmNzBkOTI2YmY4MDdiNGYwNDM3In19fQ==")
                        .build(),

                (player1, inventoryClickEvent) -> {

                    if (inventoryClickEvent.isLeftClick()) {
                        auctionItemFilter = auctionItemFilter.next();
                    } else if (inventoryClickEvent.isRightClick()) {
                        auctionItemFilter = auctionItemFilter.back();
                    }

                    updateInventory(player);
                });
    }


    private BukkitRunnable bukkitRunnable;

    @Override
    public void onOpen(Player player) {
        this.bukkitRunnable = new BukkitRunnable() {

            private final Material[] storageMaterial = {
                    Material.ANVIL,
                    Material.FURNACE,
                    Material.WORKBENCH,
                    Material.CHEST,
                    Material.ENDER_CHEST,
                    Material.DROPPER,
                    Material.PISTON_BASE,
                    Material.DIAMOND_ORE
            };

            @Override
            public void run() {
                ItemStack itemStack = ItemUtil.newBuilder(storageMaterial[NumberUtil.randomInt(0, storageMaterial.length)])
                        .setName("§aХранилище товаров")
                        .addLore("")
                        .addLore("§7Здесь будут собраны Ваши товары,")
                        .addLore("§7что когда-то ранее были выставлены на аукцион,")
                        .addLore("§7но истекли свой срок")

                        .addLore("")
                        .addLore("§7Также Вы сможете выкупить их за §e25%")
                        .addLore("§7от былой стоимости товара!")

                        .addLore("")
                        .addLore("§7Количество товаров: §f" + AuctionConfiguration.INSTANCE.getExpiredPlayerItems(player.getName()).size())
                        .addLore("")

                        .addLore("§a▸ Нажмите, чтобы перейти!")
                        .build();

                setClickItem(41, itemStack, (player1, inventoryClickEvent) -> new AuctionExpiredStorageInventory().openInventory(player));
                getBukkitInventory().setItem(40, itemStack);
            }
        };

        bukkitRunnable.runTaskTimer(PlazmixEssentialsPlugin.getInstance(), 0, 10);
    }

    @Override
    public void onClose(Player player) {
        if (bukkitRunnable != null)
            bukkitRunnable.cancel();
    }

}
