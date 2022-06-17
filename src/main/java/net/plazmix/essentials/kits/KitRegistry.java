package net.plazmix.essentials.kits;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.plazmix.coreconnector.core.group.Group;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KitRegistry {

    public static final KitRegistry INSTANCE = new KitRegistry();

    @Getter
    private final Map<Group, KitCreator> groupKitsMap = new HashMap<>();


    public KitCreator register(@NonNull Group group, @NonNull KitCreator kitCreator) {
        groupKitsMap.put(group, kitCreator);

        return kitCreator;
    }

    public KitCreator getGroup(@NonNull Group group) {
        return groupKitsMap.get(group);
    }

}
