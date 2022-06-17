package net.plazmix.essentials.shop.inventory;

import net.plazmix.essentials.shop.ShopCategory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import net.plazmix.inventory.impl.BaseSimpleInventory;
import net.plazmix.utility.ItemUtil;

public class ShopCategorySelectInventory extends BaseSimpleInventory {

    public ShopCategorySelectInventory() {
        super("Магазин", 6);
    }

    @Override
    public void drawInventory(Player player) {
        for (ShopCategory shopCategory : ShopCategory.SHOP_CATEGORIES)

            setClickItem(shopCategory.getInventorySlot(), shopCategory.getIconStack(),
                    (player1, inventoryClickEvent) -> new ShopItemsInventory(shopCategory).openInventory(player));

        setOriginalItem(5, ItemUtil.newBuilder(Material.SIGN)
                .setName("§6Общая информация")
                .addLore("§7Всего категорий: §f" + ShopCategory.SHOP_CATEGORIES.length)
                .build());
    }
}
