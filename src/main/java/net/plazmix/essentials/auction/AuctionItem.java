package net.plazmix.essentials.auction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import net.plazmix.utility.player.PlazmixUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.NumberUtil;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuctionItem {


    // TODO: Сделать ограничение по времени до 5 дней.

    public static AuctionItem create(int price, @NonNull ItemStack itemStack, @NonNull String itemOwner) {
        return new AuctionItem(price, itemStack, RandomStringUtils.randomAlphanumeric(64), itemOwner, System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
    }

    public static AuctionItem parse(@NonNull ConfigurationSection configurationSection) {
        int itemPrice       = configurationSection.getInt("Price");
        long expireTime     = configurationSection.getLong("Expire");

        ItemStack itemStack = configurationSection.getItemStack("Item", ItemUtil.newBuilder(Material.BEDROCK).setName(ChatColor.RED + "Неизвестный предмет").build());
        String itemOwner    = configurationSection.getString("Owner");

        return new AuctionItem(itemPrice, itemStack, configurationSection.getName(), itemOwner, expireTime);
    }


    private int itemPrice;

    private ItemStack itemStack;

    private final String sectionName;
    private final String itemOwner;

    private final long expireTime;


    public void purchase(int amount, @NonNull Player player) {

        int amountPrice = getAmountPrice(amount);
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        if (plazmixUser.getCoins() < amountPrice) {
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §cОшибка, у Вас недостаточно средств! Необходимо еще " + (amountPrice - plazmixUser.getCoins()) + " коинов");
            return;
        }

        PlayerInventory playerInventory = plazmixUser.handle().getInventory();

        if (Arrays.stream(playerInventory.getStorageContents()).noneMatch(content -> content != null && content.getTypeId() > 0)) {
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §cОшибка, у Вас недостаточно места в инвентаре!");
            return;
        }

        plazmixUser.removeCoins(amountPrice);
        plazmixUser.of(itemOwner).addCoins(amountPrice);

        itemStack.setAmount(Math.max(1, itemStack.getAmount() - amount));
        itemPrice -= amountPrice;

        playerInventory.addItem(ItemUtil.newBuilder(itemStack)
                .setAmount(amount)
                .build());

        plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно приобрели продукт с аукциона за §e"
                + NumberUtil.formattingSpaced(amountPrice, "коин", "коина", "коинов"));

        if (itemStack.getAmount() <= 1) {
            AuctionConfiguration.INSTANCE.delete(this);

        } else {

            AuctionConfiguration.INSTANCE.saveItem(this);
        }
    }

    public boolean isExpired() {
        return expireTime - System.currentTimeMillis() <= 0;
    }

    public int getAmountPrice(int amount) {
        return (itemPrice / itemStack.getAmount()) * amount;
    }

}
