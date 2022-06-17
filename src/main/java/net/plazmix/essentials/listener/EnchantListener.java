package net.plazmix.essentials.listener;

import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.essentials.PlazmixEssentialsPlugin;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;

public final class EnchantListener implements Listener {

    private final Map<PlazmixUser, ItemStack> PLAYER_ENCHANT = new HashMap<>();

    @EventHandler()
    private void onClick(InventoryClickEvent event) {
        PlazmixUser plazmixUser = PlazmixUser.of((Player) event.getWhoClicked());

        if (plazmixUser.getGroup() != Group.STAR) {
            return;
        }

        if (plazmixUser.getCoins() < 1500) {
            return;
        }

        ItemStack current = event.getCurrentItem();
        // если это первый клик
        if (!PLAYER_ENCHANT.containsKey(plazmixUser)) {
            if (current.getType().equals(Material.ENCHANTED_BOOK)) {
                PLAYER_ENCHANT.put(plazmixUser, current);
                
                plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fКликните по предмету, который нужно зачаровать");
            }
            return;
        }

        // если это второй клик
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) PLAYER_ENCHANT.get(plazmixUser).getItemMeta();
        try {
            if (current == null) {
                return;
            }

            current.addEnchantments(meta.getStoredEnchants());
            event.setCancelled(true);

            Bukkit.getScheduler().runTask(PlazmixEssentialsPlugin.getInstance(), () -> plazmixUser.handle().getInventory().setItem(event.getSlot(), current));

            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: §fВы успешно зачаровали выбранный предмет.");
        } catch (IllegalArgumentException ex) {
            plazmixUser.handle().sendMessage("§d§lPlazmix §8:: " + (current.getType().equals(Material.AIR) ? "§cОшибка, этот предмет нельзя зачаровать." : "§cОшибка, Вы вернули зачарование в книгу."));
        }

        PLAYER_ENCHANT.remove(plazmixUser);
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            PLAYER_ENCHANT.remove(event.getPlayer());
        }
    }

    @EventHandler
    private void onDrop(PlayerDropItemEvent e) {
        PLAYER_ENCHANT.remove(e.getPlayer());
    }
}
