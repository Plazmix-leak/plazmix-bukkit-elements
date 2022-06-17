package net.plazmix.essentials.listener;

import lombok.NonNull;
import net.plazmix.core.PlazmixCoreApi;
import net.plazmix.coreconnector.CoreConnector;
import net.plazmix.coreconnector.direction.bukkit.event.PlayerLanguageChangeEvent;
import net.plazmix.coreconnector.module.type.coloredprefix.PlayerPrefixColorChangeEvent;
import net.plazmix.coreconnector.utility.server.ServerMode;
import net.plazmix.coreconnector.utility.server.ServerSubModeType;
import net.plazmix.game.utility.hotbar.GameHotbar;
import net.plazmix.game.utility.hotbar.GameHotbarBuilder;
import net.plazmix.skin.PlayerSkinManager;
import net.plazmix.utility.ItemUtil;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class ItemsListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlazmixUser plazmixUser = PlazmixUser.of(event.getPlayer());

        plazmixUser.handle().getInventory().clear();
        this.giveHotbar(plazmixUser);
    }

    @EventHandler
    public void onPrefixUpdate(PlayerPrefixColorChangeEvent event) {
        PlazmixUser plazmixUser = PlazmixUser.of(event.getPlayerName());
        this.giveHotbar(plazmixUser);
    }

    @EventHandler
    public void onLanguageChange(PlayerLanguageChangeEvent event) {
        PlazmixUser plazmixUser = PlazmixUser.of(event.getPlayerName());
        this.giveHotbar(plazmixUser);
    }

    public void giveHotbar(@NonNull PlazmixUser plazmixUser) {
        GameHotbarBuilder lobbiedHotbar = GameHotbarBuilder.newBuilder()
                .setMoveItems(false)

                .addItem(1, ItemUtil.newBuilder(Material.COMPASS)
                                .setName("§d§lИгровое меню §7[ПКМ]")

                                .addLore("§7Меню для перемещения по режимам,")
                                .addLore("§7возращения в главный хаб сервера")

                                .build(),

                        player -> PlazmixCoreApi.dispatchCommand(player, "/menu"))

                .addItem(2, ItemUtil.newBuilder(Material.SKULL_ITEM)
                                .setDurability(3)
                                .setPlayerSkull(plazmixUser.getName())

                                .setName("§6§lПрофиль игрока " + plazmixUser.getDisplayName() + " §7[ПКМ]")

                                .addLore("§7Ваш профиль, просмотр статистики")
                                .addLore("§7вся информация о Вас")

                                .build(),

                        player -> PlazmixCoreApi.dispatchCommand(player, "/profile"))

                .addItem(8, ItemUtil.newBuilder(Material.SKULL_ITEM)
                                .setName("§a§lДонат §7[ПКМ]")

                                .addLore("§7Нажмите правой кнопкой мыши,")
                                .addLore("§7чтобы открыть меню привилегий")

                                .setDurability(3)
                                .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNjN2Y2NDQxYmQ3MWZjOTc0ZTk5NzdiY2IyMmVmYmM0YjYxMjc3YzQ5ZWZiMjQyM2FiOTE1NDg5NWJlIn19fQ==")

                                .build(),

                        player -> PlazmixCoreApi.dispatchCommand(player, "/donate"))

                .addItem(9, ItemUtil.newBuilder(Material.SKULL_ITEM)
                                .setName("§e§lВыбор лобби §7[ПКМ]")

                                .addLore("§7Нажмите правой кнопкой мыши,")
                                .addLore("§7чтобы открыть меню выбора лобби")

                                .setDurability(3)
                                .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYThmZWQxMjI4NWYxZDliZjI0NWZlZGU0MTQ3YjhjZGE5M2Q1OTM4M2NlOGE1OGQ2Mjk5ZGQ0ZWFlOTg0MyJ9fX0=")

                                .build(),

                        player -> PlazmixCoreApi.dispatchCommand(player, "/selector"));

        if (ServerMode.getSubMode(CoreConnector.getInstance().getServerName()).getType() == ServerSubModeType.GAME_LOBBY) {
            lobbiedHotbar.addItem(5, ItemUtil.newBuilder(Material.SKULL_ITEM)
                    .setDurability(3)
                    .setTextureValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFlZWFkMWUxN2VjZjUzZjIyMjQ3NWRhZWViYWQzNTI2MTM3Yjc5N2U1Y2I3NjdiOThhYzVlN2ViYjlmZTkifX19")

                    .setName("§b§lБыстрый старт §7[ПКМ]")

                    .addLore("§7Нажмите правой кнопкой мыши,")
                    .addLore("§7чтобы открыть меню быстрого старта")
                    .build(),

                    player -> PlazmixCoreApi.dispatchCommand(player, "/gameselector"));
        }

        lobbiedHotbar.build().setHotbarTo(plazmixUser.handle());
    }
}
