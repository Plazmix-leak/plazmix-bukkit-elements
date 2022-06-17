package net.plazmix.essentials.shop.inventory;

import lombok.NonNull;
import net.plazmix.essentials.shop.ShopCategory;
import net.plazmix.essentials.shop.ShopItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.plazmix.inventory.impl.BasePaginatedInventory;
import net.plazmix.utility.ItemUtil;

public class ShopItemsInventory
        extends BasePaginatedInventory {

    private final ShopCategory shopCategory;

    public ShopItemsInventory(@NonNull ShopCategory shopCategory) {
        super(shopCategory.getItemDisplay(), 5);

        this.shopCategory = shopCategory;
    }

    @Override
    public void drawInventory(Player player) {
        addRowToMarkup(2, 1);
        addRowToMarkup(3, 1);
        addRowToMarkup(4, 1);

        for (ShopItem shopItem : shopCategory.getCategoryItems()) {
            addClickItemToMarkup(shopItem.getShopStack(player), (player1, inventoryClickEvent) -> shopItem.purchase(player));
        }

        setClickItem(41, ItemUtil.newBuilder(Material.ANVIL)
                .setName("§aВернуться назад")
                .addLore("§7Нажмите, чтобы вернуться в список категорий")
                .build(),

                (player1, inventoryClickEvent) -> new ShopCategorySelectInventory().openInventory(player));
    }

}
