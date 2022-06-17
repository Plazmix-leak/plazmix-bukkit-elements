package net.plazmix.essentials.kits;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KitCreator {

    public static KitCreator newBuilder() {
        return new KitCreator();
    }

    private ItemStack helmetItem, chestplateItem, leggingsItem, bootsItem;
    private ItemStack offhandItem;

    private final List<ItemStack> storageItems = new ArrayList<>();


    public KitCreator setHelmetItem(@NonNull ItemStack helmetItem) {
        this.helmetItem = helmetItem;
        return this;
    }

    public KitCreator setChestplateItem(@NonNull ItemStack chestplateItem) {
        this.chestplateItem = chestplateItem;
        return this;
    }

    public KitCreator setLeggingsItem(@NonNull ItemStack leggingsItem) {
        this.leggingsItem = leggingsItem;
        return this;
    }

    public KitCreator setBootsItem(@NonNull ItemStack bootsItem) {
        this.bootsItem = bootsItem;
        return this;
    }

    public KitCreator setOffhandItem(@NonNull ItemStack offhandItem) {
        this.offhandItem = offhandItem;
        return this;
    }

    public KitCreator addItem(@NonNull ItemStack itemStack) {
        storageItems.add(itemStack);
        return this;
    }


    public void set(@NonNull Player player) {
        PlayerInventory playerInventory = player.getInventory();

        playerInventory.setHelmet(helmetItem);
        playerInventory.setChestplate(chestplateItem);
        playerInventory.setLeggings(leggingsItem);
        playerInventory.setBoots(bootsItem);

        playerInventory.setItemInOffHand(offhandItem);

        storageItems.forEach(playerInventory::addItem);
    }

}
