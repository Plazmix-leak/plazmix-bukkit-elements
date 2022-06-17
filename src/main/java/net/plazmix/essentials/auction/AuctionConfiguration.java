package net.plazmix.essentials.auction;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import net.plazmix.configuration.BaseConfiguration;
import net.plazmix.essentials.PlazmixEssentialsPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public final class AuctionConfiguration extends BaseConfiguration {

    public static final AuctionConfiguration INSTANCE
            = new AuctionConfiguration(PlazmixEssentialsPlugin.getInstance());

    @Getter
    private final Collection<AuctionItem> auctionItemCollection = new ArrayList<>();


    private AuctionConfiguration(@NonNull Plugin plugin) {
        super(plugin, "auction_items.yml");
    }

    @Override
    protected void onInstall(@NonNull FileConfiguration fileConfiguration) {
        ConfigurationSection configurationSection = fileConfiguration.getConfigurationSection("Items");

        if (configurationSection == null) {
            return;
        }

        for (String itemKey : configurationSection.getKeys(false)) {

            ConfigurationSection itemSection = configurationSection.getConfigurationSection(itemKey);
            AuctionItem auctionItem = AuctionItem.parse(itemSection);

            auctionItemCollection.add(auctionItem);
        }
    }

    public void saveItem(AuctionItem auctionItem) {
        String sectionPrefix = ("Items." + auctionItem.getSectionName());

        getLoadedConfiguration().set(sectionPrefix + ".Price", auctionItem.getItemPrice());
        getLoadedConfiguration().set(sectionPrefix + ".Expire", auctionItem.getExpireTime());
        getLoadedConfiguration().set(sectionPrefix + ".Item", auctionItem.getItemStack());
        getLoadedConfiguration().set(sectionPrefix + ".Owner", auctionItem.getItemOwner());

        saveConfiguration();
    }

    public void delete(AuctionItem auctionItem) {
        getLoadedConfiguration().set("Items." + auctionItem.getSectionName(), null);

        auctionItemCollection.remove(auctionItem);
    }

    public Collection<AuctionItem> getPlayerItems(@NonNull String playerName) {
        return auctionItemCollection.stream().filter(auctionItem -> auctionItem.getItemOwner().equalsIgnoreCase(playerName) && !auctionItem.isExpired())
                .collect(Collectors.toList());
    }

    public Collection<AuctionItem> getExpiredPlayerItems(@NonNull String playerName) {
        return auctionItemCollection.stream().filter(auctionItem -> auctionItem.getItemOwner().equalsIgnoreCase(playerName) && auctionItem.isExpired())
                .collect(Collectors.toList());
    }

}
