package net.plazmix.essentials.command.shop;

import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.essentials.shop.inventory.ShopCategorySelectInventory;

public class ShopCommand extends BaseCommand<Player> {

    public ShopCommand() {
        super("shop", "magaz", "магаз", "магазин");
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        new ShopCategorySelectInventory().openInventory(player);
    }

}
