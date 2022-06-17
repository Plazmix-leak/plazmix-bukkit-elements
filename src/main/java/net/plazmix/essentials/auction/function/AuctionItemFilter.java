package net.plazmix.essentials.auction.function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.plazmix.essentials.auction.AuctionItem;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Getter
public enum AuctionItemFilter {

    OF_1_HOUR("За последний час", auctionItem -> TimeUnit.DAYS.toMillis(1) - (auctionItem.getExpireTime() - System.currentTimeMillis()) <= TimeUnit.HOURS.toMillis(1)),
    OF_5_HOURS("За последние 5 часов", auctionItem -> TimeUnit.DAYS.toMillis(1) - (auctionItem.getExpireTime() - System.currentTimeMillis()) <= TimeUnit.HOURS.toMillis(5)),
    OF_10_HOURS("За последние 10 часов", auctionItem -> TimeUnit.DAYS.toMillis(1) - (auctionItem.getExpireTime() - System.currentTimeMillis()) <= TimeUnit.HOURS.toMillis(10)),
    OF_15_HOURS("За последние 15 часов", auctionItem -> TimeUnit.DAYS.toMillis(1) - (auctionItem.getExpireTime() - System.currentTimeMillis()) <= TimeUnit.HOURS.toMillis(15)),

    OF_DAY("За сутки", auctionItem -> !auctionItem.isExpired());


    private final String sortingName;
    private final Predicate<? super AuctionItem> itemFilter;


    public AuctionItemFilter next() {
        if (ordinal() == values().length - 1)
            return values()[0];

        return values()[ordinal() + 1];
    }

    public AuctionItemFilter back() {
        if (ordinal() <= 0)
            return values()[values().length - 1];

        return values()[ordinal() - 1];
    }
}
