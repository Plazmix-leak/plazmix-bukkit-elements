package net.plazmix.essentials.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import net.plazmix.game.item.GameItemPrice;
import net.plazmix.utility.ItemUtil;

import java.util.Collection;
import java.util.LinkedList;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum ShopCategory {

    BLOCKS(21, "Блоки", Material.GRASS) {{

        addItem(ShopItem.create(Material.GRASS, "Блок травы", 4, GameItemPrice.create(320, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIRT, "Земля", 16, GameItemPrice.create(160, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIRT, "Каменистая земля", 1, 32, GameItemPrice.create(400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GRAVEL, "Гравий", 16, GameItemPrice.create(240, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SNOW_BLOCK, "Снег", 4, GameItemPrice.create(60, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GLOWSTONE, "Светопыль", 4, GameItemPrice.create(380, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COBBLESTONE, "Булыжник", 8, GameItemPrice.create(24, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Камень", 8, GameItemPrice.create(24, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Гранит", 1, 32, GameItemPrice.create(730, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Полированный гранит", 2, 32, GameItemPrice.create(340, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Диорит", 3, 32, GameItemPrice.create(210, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Полированный диорит", 4, 32, GameItemPrice.create(340, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Андезит", 5, 32, GameItemPrice.create(225, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE, "Полированный андезит", 6, 32, GameItemPrice.create(360, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.WOOD, "Дубовые доски", 0, 32, GameItemPrice.create(730, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD, "Еловые доски", 1, 32, GameItemPrice.create(740, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD, "Берёзовые доски", 2, 32, GameItemPrice.create(720, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD, "Доски из тропического дерева", 3, 32, GameItemPrice.create(710, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD, "Акациевые доски", 4, 32, GameItemPrice.create(760, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD, "Доски из тёмного дуба", 5, 32, GameItemPrice.create(790, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.SAND, "Песок", 32, GameItemPrice.create(1050, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SANDSTONE, "Песчаник", 32, GameItemPrice.create(800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GLASS, "Стекло", 32, GameItemPrice.create(325, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.BRICK, "Кирпичный блок", 32, GameItemPrice.create(1050, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.OBSIDIAN, "Обсидиан", 8, GameItemPrice.create(600, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.MAGMA, "Магма", 4, GameItemPrice.create(180, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PRISMARINE, "Призмарин", 12, GameItemPrice.create(216, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PRISMARINE, "Призмариновые кирпичи", 1, 12, GameItemPrice.create(220, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SEA_LANTERN, "Морской фонарь", 4, GameItemPrice.create(220, GameItemPrice.PriceCurrency.COINS)));
    }},

    ARMOR(22, "Броня", Material.IRON_CHESTPLATE) {{

        addItem(ShopItem.create(Material.LEATHER_HELMET, "Кожанный шлем", GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.LEATHER_CHESTPLATE, "Кожанный нагрудник", GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.LEATHER_LEGGINGS, "Кожанные штаны", GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.LEATHER_BOOTS, "Кожанные ботинки", GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.CHAINMAIL_HELMET, "Кольчужный шлем", GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.CHAINMAIL_CHESTPLATE, "Кольчужный нагрудник", GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.CHAINMAIL_LEGGINGS, "Кольчужные поножи", GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.CHAINMAIL_BOOTS, "Кольчужные ботинки", GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.GOLD_HELMET, "Золотой шлем", GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_CHESTPLATE, "Золотой нагрудник", GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_LEGGINGS, "Золотые поножи", GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_BOOTS, "Золотые ботинки", GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.IRON_HELMET, "Железный шлем", GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_CHESTPLATE, "Железный нагрудник", GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_LEGGINGS, "Железные поножи", GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_BOOTS, "Железные ботинки", GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.DIAMOND_HELMET, "Алмазный шлем", GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_CHESTPLATE, "Алмазный нагрудник", GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_LEGGINGS, "Алмазные поножи", GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_BOOTS, "Алмазные ботинки", GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.SHIELD, "Щит", GameItemPrice.create(2000, GameItemPrice.PriceCurrency.COINS)));
    }},

    REDSTONE(23, "Механизмы", Material.PISTON_BASE) {{

        addItem(ShopItem.create(Material.REDSTONE, "Красная пыль", 16, GameItemPrice.create(550, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PISTON_BASE, "Поршень", 1, GameItemPrice.create(550, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PISTON_STICKY_BASE, "Липкий поршень", 1, GameItemPrice.create(730, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DROPPER, "Выбрасыватель", 1, GameItemPrice.create(750, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DISPENSER, "Раздатчик", 1, GameItemPrice.create(750, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.HOPPER, "Воронка", 1, GameItemPrice.create(750, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DAYLIGHT_DETECTOR, "Датчик дневного света", 1, GameItemPrice.create(930, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.REDSTONE_LAMP_OFF, "Лампа", 1, GameItemPrice.create(750, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.OBSERVER, "Наблюдатель", 1, GameItemPrice.create(1300, GameItemPrice.PriceCurrency.COINS)));
    }},

    ORE(24, "Руды", Material.DIAMOND_ORE) {{

        addItem(ShopItem.create(Material.LAPIS_ORE, "Лазуритная руда", 16, GameItemPrice.create(660, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.REDSTONE_ORE, "Редстоуновая руда", 16, GameItemPrice.create(540, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_ORE, "Железная руда", 4, GameItemPrice.create(400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_ORE, "Золотая руда", 4, GameItemPrice.create(520, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.QUARTZ_ORE, "Кварцевая руда", 4, GameItemPrice.create(320, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_ORE, "Алмазная руда", 4, GameItemPrice.create(1600, GameItemPrice.PriceCurrency.COINS)));
    }},

    TOOLS(25, "Инструменты", Material.GOLD_AXE) {{

        addItem(ShopItem.create(Material.WOOD_SWORD, "Деверянный меч", 1, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD_SPADE, "Деверянная лопата", 1, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD_AXE, "Деверянный топор", 1, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WOOD_PICKAXE, "Деверянная кирка", 1, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.STONE_SWORD, "Каменный меч", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE_AXE, "Каменный топор", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE_PICKAXE, "Каменная кирка", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.STONE_SPADE, "Каменная лопата", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.GOLD_SWORD, "Золотой меч", 1, GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_AXE, "Золотой топор", 1, GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_PICKAXE, "Золотая кирка", 1, GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_SPADE, "Золотая лопата", 1, GameItemPrice.create(700, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.IRON_SWORD, "Железный меч", 1, GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_AXE, "Железный топор", 1, GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_PICKAXE, "Железная кирка", 1, GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_SPADE, "Железная лопата", 1, GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.DIAMOND_SWORD, "Алмазный меч", 1, GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_AXE, "Алмазный топор", 1, GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_PICKAXE, "Алмазная кирка", 1, GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.DIAMOND_SPADE, "Алмазная лопата", 1, GameItemPrice.create(2800, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.ELYTRA, "Элитра", GameItemPrice.create(15000, GameItemPrice.PriceCurrency.COINS)));

        addItem(ShopItem.create(Material.FISHING_ROD, "Удочка", 1, GameItemPrice.create(1400, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SHEARS, "Ножницы", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.FLINT_AND_STEEL, "Огниво", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COMPASS, "Компас", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WATCH, "Часы", 1, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
    }},

    POTIONS(31, "Разное", Material.GLASS_BOTTLE) {{
        addItem(ShopItem.create(Material.DOUBLE_PLANT, "Подсолнух", 4, GameItemPrice.create(30, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SADDLE, "Седло", GameItemPrice.create(500, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.LEASH, "Поводок", GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.NAME_TAG, "Бирка", GameItemPrice.create(800, GameItemPrice.PriceCurrency.COINS)));
    }},

    FOOD(32, "Еда", Material.GOLDEN_APPLE) {{

        addItem(ShopItem.create(Material.APPLE, "Яблоко", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.BREAD, "Хлеб", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PORK, "Сырая свинина", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GRILLED_PORK, "Жареная свинина", 16, GameItemPrice.create(250, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLDEN_APPLE, "Золотое яблоко", 8, GameItemPrice.create(850, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COOKED_FISH, "Жареная треска", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.CAKE, "Торт", 1, GameItemPrice.create(250, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COOKIE, "Печенье", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COOKED_CHICKEN, "Жареная курица", 16, GameItemPrice.create(260, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.CARROT_ITEM, "Морковь", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COOKED_MUTTON, "Жареная баранина", 16, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
    }},

    FARMING(33, "Фермерство", Material.WHEAT) {{

        addItem(ShopItem.create(Material.WHEAT, "Пшеница", 16, GameItemPrice.create(480, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SEEDS, "Семена пшеницы", 12, GameItemPrice.create(240, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PUMPKIN_SEEDS, "Семена тыквы", 12, GameItemPrice.create(540, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.MELON_SEEDS, "Семена арзбуза", 12, GameItemPrice.create(540, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.INK_SACK, "Какао-бобы", 3,12, GameItemPrice.create(1020, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.NETHER_STALK, "Нарост из Незера", 4, GameItemPrice.create(620, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.BEETROOT_SEEDS, "Семена свёклы", 4, GameItemPrice.create(340, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SUGAR_CANE, "Сахарный тростник", 16, GameItemPrice.create(600, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SAPLING, "Сажанец дуба", 0, 4, GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.SAPLING, "Еловый сажанец дуба", 1, 4,GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.BROWN_MUSHROOM, "Гриб", 8, GameItemPrice.create(800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.RED_MUSHROOM, "Гриб", 8, GameItemPrice.create(800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.PUMPKIN, "Тыква", 4, GameItemPrice.create(480, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.INK_SACK, "Чернильный мешок", 10, GameItemPrice.create(350, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.BONE, "Кость", 16, GameItemPrice.create(240, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.BUCKET, "Ведро", 1, GameItemPrice.create(100, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.WATER_BUCKET, "Ведро воды", 1, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.LAVA_BUCKET, "Ведро лавы", 1, GameItemPrice.create(150, GameItemPrice.PriceCurrency.COINS)));
    }},
    RESOURCE(41, "Ресурсы", Material.COAL) {{

        addItem(ShopItem.create(Material.DIAMOND, "Алмаз", 1, GameItemPrice.create(800, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.QUARTZ, "Кварц", 4, GameItemPrice.create(120, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.IRON_INGOT, "Железо", 4, GameItemPrice.create(200, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.GOLD_INGOT, "Золото", 4, GameItemPrice.create(260, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.EMERALD, "Изумруд", 1, GameItemPrice.create(450, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.REDSTONE, "Редстоун", 12, GameItemPrice.create(300, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.INK_SACK, "Лазурит", 4,9, GameItemPrice.create(162, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COAL, "Уголь", 8, GameItemPrice.create(280, GameItemPrice.PriceCurrency.COINS)));
        addItem(ShopItem.create(Material.COAL, "Древесный уголь", 1, 8, GameItemPrice.create(280, GameItemPrice.PriceCurrency.COINS)));
    }},
    ;

    public static final ShopCategory[] SHOP_CATEGORIES = values();


    private final int inventorySlot;

    private final String itemDisplay;

    private final Material iconMaterial;
    private int durability;

    private final Collection<ShopItem> categoryItems = new LinkedList<>();


    protected void addItem(@NonNull ShopItem shopItem) {
        categoryItems.add(shopItem);
    }

    public ItemStack getIconStack() {
        ItemUtil.ItemBuilder itemBuilder = ItemUtil.newBuilder(iconMaterial);
        itemBuilder.setDurability(durability);

        itemBuilder.setName(ChatColor.YELLOW + itemDisplay);
        itemBuilder.addLore("");

        itemBuilder.addLore("§7Всего предметов: §f" + categoryItems.size());
        itemBuilder.addLore("§7В данной категории продается:");

        int itemCounter = 0;
        for (ShopItem shopItem : getCategoryItems()) {

            if (itemCounter >= 10) {
                itemBuilder.addLore("   §8и т.д.");
                break;
            }

            itemBuilder.addLore(" §8• §f" + shopItem.getDisplayName() + " §7(x" + shopItem.getItemStack().getAmount() + ")");
            itemCounter++;
        }

        itemBuilder.addLore("");
        itemBuilder.addLore("§e▸ Нажмите, чтобы открыть!");

        for (ItemFlag itemFlag : ItemFlag.values())
            itemBuilder.addItemFlag(itemFlag);

        return itemBuilder.build();
    }
}
