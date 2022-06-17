package net.plazmix.essentials.command.auction;

import lombok.NonNull;
import net.plazmix.essentials.auction.AuctionConfiguration;
import net.plazmix.essentials.auction.AuctionItem;
import net.plazmix.essentials.auction.inventory.AuctionItemsInventory;
import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.plazmix.command.BaseCommand;
import net.plazmix.utility.NumberUtil;
import net.plazmix.utility.ValidateUtil;

public class AuctionCommand extends BaseCommand<Player> {

    public AuctionCommand() {
        super("auc", "auction", "аукцион");
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);
        if (args.length == 0) {
            sendHelpMessage(plazmixUser);

            return;
        }

        switch (args[0].toLowerCase()) {
            case "sell": {
                if (args.length < 2) {
                    plazmixUser.localization().sendMessage("AUCTION_SELL_FORMAT");
                    break;
                }

                if (!ValidateUtil.isNumber(args[1])) {
                    plazmixUser.localization().sendMessage("AUCTION_SELL_ERROR_NOT_NUMBER");
                    return;
                }

                int itemPrice = Integer.parseInt(args[1]);

                if (itemPrice <= 0) {
                    plazmixUser.localization().sendMessage("AUCTION_SELL_ERROR_NOT_NUMBER");
                    return;
                }

                ItemStack itemStack = player.getItemInHand().clone();

                if (itemStack == null || itemStack.getTypeId() <= 0) {
                    plazmixUser.localization().sendMessage("AUCTION_SELL_ERROR_NOT_ITEM");
                    break;
                }

                player.setItemInHand(null);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);

                plazmixUser.localization().sendMessage(localizationResource -> localizationResource.getMessage("AUCTION_SELL_SUCCESS_ADD_ITEM")
                        .replace("%money%", NumberUtil.formattingSpaced(itemPrice, "монету", "монеты", "монет"))
                        .toText());

                AuctionItem auctionItem = AuctionItem.create(itemPrice, itemStack, player.getName());

                AuctionConfiguration.INSTANCE.getAuctionItemCollection().add(auctionItem);
                AuctionConfiguration.INSTANCE.saveItem(auctionItem);
                break;
            }

            case "list":
            case "menu": {

                new AuctionItemsInventory().openInventory(player);
                break;
            }

            default:
                sendHelpMessage(plazmixUser);
        }
    }

    private void sendHelpMessage(@NonNull PlazmixUser plazmixUser) {
        plazmixUser.localization().sendMessage("AUCTION_HELP_MESSAGE");
    }

}
