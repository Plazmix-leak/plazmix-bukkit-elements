package net.plazmix.essentials;

import lombok.Getter;
import net.plazmix.PlazmixApi;
import net.plazmix.coreconnector.CoreConnector;
import net.plazmix.coreconnector.core.group.Group;
import net.plazmix.coreconnector.utility.server.ServerMode;
import net.plazmix.coreconnector.utility.server.ServerSubModeType;
import net.plazmix.essentials.auction.AuctionConfiguration;
import net.plazmix.essentials.command.auction.AuctionCommand;
import net.plazmix.essentials.command.essentials.*;
import net.plazmix.essentials.command.location.RandomTeleportCommand;
import net.plazmix.essentials.command.location.SpawnCommand;
import net.plazmix.essentials.command.shop.ShopCommand;
import net.plazmix.essentials.home.PlayerHomeConfiguration;
import net.plazmix.essentials.kits.KitCreator;
import net.plazmix.essentials.kits.KitRegistry;
import net.plazmix.essentials.listener.*;
import net.plazmix.essentials.region.RegionConfiguration;
import net.plazmix.utility.ItemUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

/*  Leaked by https://t.me/leak_mine
    - Все слитые материалы вы используете на свой страх и риск.

    - Мы настоятельно рекомендуем проверять код плагинов на хаки!
    - Список софта для декопиляции плагинов:
    1. Luyten (последнюю версию можно скачать можно тут https://github.com/deathmarine/Luyten/releases);
    2. Bytecode-Viewer (последнюю версию можно скачать можно тут https://github.com/Konloch/bytecode-viewer/releases);
    3. Онлайн декомпиляторы https://jdec.app или http://www.javadecompilers.com/

    - Предложить свой слив вы можете по ссылке @leakmine_send_bot или https://t.me/leakmine_send_bot
*/

public final class PlazmixEssentialsPlugin
        extends JavaPlugin {

    @Getter
    private static PlazmixEssentialsPlugin instance;

    {
        instance = this;
    }

    private void registerGameModeCommands() {
        PlazmixApi.registerCommand(new GamemodeCommand(null), "gamemode", "gm", "режим");
        PlazmixApi.registerCommand(new GamemodeCommand(GameMode.CREATIVE), "gmc");
        PlazmixApi.registerCommand(new GamemodeCommand(GameMode.SURVIVAL), "gms");
        PlazmixApi.registerCommand(new GamemodeCommand(GameMode.ADVENTURE), "gma");
        PlazmixApi.registerCommand(new GamemodeCommand(GameMode.SPECTATOR), "gmsp");
    }

    @Override
    public void onEnable() {
        ServerMode serverMode = ServerMode.getMode(CoreConnector.getInstance().getServerName());
        ServerSubModeType serverSubModeType = ServerMode.getSubMode(CoreConnector.getInstance().getServerName()).getType();

        switch (serverSubModeType) {

            case GAME_ARENA: {
                this.registerGameModeCommands();
                break;
            }

            case MAIN: {
                getServer().getPluginManager().registerEvents(new StaticChatListener(), this);
                getServer().getPluginManager().registerEvents(new ItemsListener(), this);
                break;
            }

            case GAME_LOBBY: {
                getServer().getPluginManager().registerEvents(new ItemsListener(), this);
                break;
            }

            case SURVIVAL: {
                RegionConfiguration.INSTANCE.createIfNotExists();
                AuctionConfiguration.INSTANCE.createIfNotExists();
                PlayerHomeConfiguration.INSTANCE.createIfNotExists();

                // Register groups kits.
                switch (serverMode) {
                    case ONEBLOCK: {
                        KitRegistry.INSTANCE.register(Group.DEFAULT, KitCreator.newBuilder()
                                .addItem(ItemUtil.newBuilder(Material.WOOD_AXE)
                                        .addEnchantment(Enchantment.DIG_SPEED, 2)
                                        .build())

                                .addItem(ItemUtil.newBuilder(Material.WOOD_PICKAXE)
                                        .addEnchantment(Enchantment.LUCK, 1)
                                        .addEnchantment(Enchantment.DIG_SPEED, 1)
                                        .build()));
                        break;
                    }

                    case PRISON: {
                    }

                    case MMORPG: {
                    }

                    // TODO: Add an other survival modes...
                }

                // Register events.
                getServer().getPluginManager().registerEvents(new StaticChatListener(), this);
                getServer().getPluginManager().registerEvents(new PlayerListener(), this);
                getServer().getPluginManager().registerEvents(new BackListener(), this);
                getServer().getPluginManager().registerEvents(new TypedChatListener(), this);
                getServer().getPluginManager().registerEvents(new EnchantListener(), this);

                // Register commands.
                this.registerGameModeCommands();

                if (serverMode != ServerMode.ONEBLOCK) {
                    PlazmixApi.registerCommand(new RandomTeleportCommand());
                }

                if (!KitRegistry.INSTANCE.getGroupKitsMap().isEmpty()) {
                    PlazmixApi.registerCommand(new KitCommand());
                }

                PlazmixApi.registerCommand(new TeleportCommand());
                PlazmixApi.registerCommand(new SpawnCommand());
                PlazmixApi.registerCommand(new ShopCommand());
                PlazmixApi.registerCommand(new AuctionCommand());
                PlazmixApi.registerCommand(new BackCommand());
                PlazmixApi.registerCommand(new ExtCommand());
                PlazmixApi.registerCommand(new FeedCommand());
                PlazmixApi.registerCommand(new FlyCommand());
                PlazmixApi.registerCommand(new HealCommand());
                PlazmixApi.registerCommand(new VanishCommand());
                PlazmixApi.registerCommand(new DayCommand());
                PlazmixApi.registerCommand(new NightCommand());
                PlazmixApi.registerCommand(new WeatherCommand());
                PlazmixApi.registerCommand(new ClearCommand());
                PlazmixApi.registerCommand(new WorkbenchCommand());
                PlazmixApi.registerCommand(new AnvilCommand());
                PlazmixApi.registerCommand(new SuicideCommand());
                PlazmixApi.registerCommand(new EnchantCommand());
                PlazmixApi.registerCommand(new EnderChestCommand());
                PlazmixApi.registerCommand(new FurnaceCommand());
                PlazmixApi.registerCommand(new GodCommand());
                PlazmixApi.registerCommand(new RepairCommand());
                PlazmixApi.registerCommand(new HomeCommand());
                PlazmixApi.registerCommand(new SethomeCommand());
                PlazmixApi.registerCommand(new RemoveHomeCommand());
            }
        }
    }
}
