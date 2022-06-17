package net.plazmix.essentials.command.essentials;

import net.plazmix.utility.player.PlazmixUser;
import org.bukkit.entity.Player;
import net.plazmix.command.BaseCommand;
import net.plazmix.coreconnector.core.group.Group;

import java.util.Locale;

public class WeatherCommand extends BaseCommand<Player> {

    public WeatherCommand() {
        super("weather");

        setMinimalGroup(Group.MODER);
    }

    @Override
    protected void onExecute(Player player, String[] args) {
        PlazmixUser plazmixUser = PlazmixUser.of(player);

        if (args.length == 0) {
            plazmixUser.localization().sendMessage("WEATHER_FORMAT");
            return;
        }

        player.getWorld().setWeatherDuration(0);

        player.getWorld().setStorm(false);
        player.getWorld().setThundering(false);

        switch (args[0].toLowerCase()) {

            case "storm":
            case "rain": {
                player.getWorld().setStorm(true);
                break;
            }

            case "thunder": {
                player.getWorld().setStorm(true);
                player.getWorld().setThundering(true);
                break;
            }
        }

        plazmixUser.localization().sendMessage(localizationResource -> localizationResource.getMessage("WEATHER_SUCCESS_CHANGE_SKY")
                .replace("%world%", player.getWorld().getName())
                .toText());
    }

}
