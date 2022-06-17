package net.plazmix.essentials.auction.function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.plazmix.essentials.auction.AuctionItem;

import java.util.Comparator;

@RequiredArgsConstructor
@Getter
public enum AuctionItemSort {

    PRICE_PLUS("Цена (по возрастанию)", Comparator.comparing(AuctionItem::getItemPrice)),
    PRICE_MINUS("Цена (по убыванию)", Comparator.comparing(auctionItem -> ((AuctionItem) auctionItem).getItemPrice()).reversed()),

    DATE_PLUS("Срок (по возрастанию)", Comparator.comparing(AuctionItem::getExpireTime)),
    DATE_MINUS("Срок (по убыванию)", Comparator.comparing(AuctionItem::getExpireTime).reversed()),

    AMOUNT_PLUS("Количество (по возрастанию)", Comparator.comparing(auctionItem -> auctionItem.getItemStack().getAmount())),
    AMOUNT_MINUS("Количество (по убыванию)", Comparator.comparing(auctionItem -> ((AuctionItem)auctionItem).getItemStack().getAmount()).reversed()),

    UNKNOWN("Стандарт", (o1, o2) -> 0);


    private final String sortingName;
    private final Comparator<? super AuctionItem> itemComparator;


    public AuctionItemSort next() {
        if (ordinal() == values().length - 1)
            return values()[0];

        return values()[ordinal() + 1];
    }

    public AuctionItemSort back() {
        if (ordinal() <= 0)
            return values()[values().length - 1];

        return values()[ordinal() - 1];
    }
}
