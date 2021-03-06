package net.plazmix.essentials.shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import net.plazmix.game.item.GameItemPrice;
import net.plazmix.utility.ItemUtil;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ShopItem {

    public static ShopItem create(@NonNull ItemStack itemStack, @NonNull String displayName, @NonNull GameItemPrice itemPrice) {
        return new ShopItem(itemStack, displayName, itemPrice);
    }

    public static ShopItem create(@NonNull Material material, @NonNull String displayName, int durability, int amount, @NonNull GameItemPrice itemPrice) {
        return new ShopItem(new ItemStack(material, amount, (byte) durability), displayName, itemPrice);
    }

    public static ShopItem create(@NonNull Material material, @NonNull String displayName, int amount, @NonNull GameItemPrice itemPrice) {
        return new ShopItem(new ItemStack(material, amount), displayName, itemPrice);
    }

    public static ShopItem create(@NonNull Material material, @NonNull String displayName, @NonNull GameItemPrice itemPrice) {
        return new ShopItem(new ItemStack(material), displayName, itemPrice);
    }

    private final ItemStack itemStack;

    private final String displayName;

    private final GameItemPrice itemPrice;


    public ItemStack getShopStack(@NonNull Player player) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        // Initialize player data.
        int playerBalance = (itemPrice.getCurrency().equals(GameItemPrice.PriceCurrency.COINS) ? plazmixUser.getCoins() : plazmixUser.getGolds());
        boolean hasBalance = playerBalance >= itemPrice.getCount();

        ChatColor chatColor = (hasBalance ? ChatColor.YELLOW : ChatColor.RED);


        // Create ItemStack for the Shop inventory.
        ItemUtil.ItemBuilder itemBuilder = ItemUtil.newBuilder(itemStack.clone());
        itemBuilder.setName(chatColor + displayName);

        itemBuilder.addLore("");
        itemBuilder.addLore("??7????????????????????: ??fx" + itemStack.getAmount());
        itemBuilder.addLore("??7????????: " + itemPrice.formattingDisplay());
        itemBuilder.addLore("");

        if (!hasBalance) {
            itemBuilder.addLore(chatColor + "?? ?????? ???????????????????????? ?????????????? ?????? ??????????????!");
            itemBuilder.addLore(chatColor + "?????? ???????????????????? ?????? " + ChatColor.stripColor(GameItemPrice.create(itemPrice.getCount() - playerBalance, itemPrice.getCurrency()).formattingDisplay(true)));

        } else {

            itemBuilder.addLore(chatColor + "??? ??????????????, ?????????? ????????????????????!");
        }

        for (ItemFlag itemFlag : ItemFlag.values())
            itemBuilder.addItemFlag(itemFlag);

        itemBuilder.removeItemFlag(ItemFlag.HIDE_ENCHANTS);

        // Build the item.
        return itemBuilder.build();
    }

    public void purchase(@NonNull Player player) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        // Initialize player data.
        int playerBalance = (itemPrice.getCurrency().equals(GameItemPrice.PriceCurrency.COINS) ? plazmixUser.getCoins() : plazmixUser.getGolds());
        boolean hasBalance = playerBalance >= itemPrice.getCount();

        if (!hasBalance) {
            player.sendMessage("??c????????????, ?? ?????? ???????????????????????? ?????????????? ?????? ?????????????? ?????????????? ????????????????!");
            return;
        }

        // Purchase action process.
        switch (itemPrice.getCurrency()) {
            case COINS:
                plazmixUser.removeCoins(itemPrice.getCount());
                break;

            case GOLDS:
                plazmixUser.removeGolds(itemPrice.getCount());
                break;
        }

        player.sendMessage("??dShop :: ??f???? ?????????????????? ??e" + ChatColor.stripColor(displayName) + " (x" + itemStack.getAmount() + ") ??f???? "
                + itemPrice.formattingDisplay());

        player.getInventory().addItem(itemStack.clone());
    }

    public ShopItem addEnchantment(int enchantmentLevel, Enchantment enchantment) {
        itemStack.addEnchantment(enchantment, enchantmentLevel);
        return this;
    }

}
