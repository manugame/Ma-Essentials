package com.maciej916.maessentials.libs;

import com.maciej916.maessentials.classes.Location;
import com.maciej916.maessentials.config.ConfigValues;
import com.maciej916.maessentials.data.DataManager;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

import java.util.Optional;

import static com.maciej916.maessentials.MaEssentials.MODID;

public class Methods {

    public static final SuggestionProvider<CommandSource> HOME_SUGGEST = (context, builder) -> ISuggestionProvider.suggest(DataManager.getPlayerData(context.getSource().asPlayer()).getHomes().keySet().stream().toArray(String[]::new), builder);

    public static final SuggestionProvider<CommandSource> WARP_SUGGEST = (context, builder) -> ISuggestionProvider.suggest(DataManager.getWarpData().getWarps().keySet().stream().toArray(String[]::new), builder);

    private static String getVersion() {
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(MODID);
        if (o.isPresent()) {
            return o.get().getModInfo().getVersion().toString();
        }
        return "NONE";
    }

    public static boolean isDev() {
        String version = getVersion();
        return version.equals("NONE");
    }

    public static TextComponent formatText(String translationKey, TextFormatting color, Object... args) {
        TextComponent msg;
        if (args != null) {
            msg = new TranslationTextComponent(translationKey, args);
        } else {
            msg = new TranslationTextComponent(translationKey);
        }
        msg.getStyle().setColor(color);
        return msg;
    }

    public static boolean isLocationSame(Location fistLocation, Location secoondLocation) {
        if (fistLocation.x == secoondLocation.x && fistLocation.y == secoondLocation.y && fistLocation.z == secoondLocation.z && fistLocation.dimension == secoondLocation.dimension ) {
            return true;
        }
        return false;
    }

    public static long delayCommand(long time, int cooldown) {
        long currentTime = System.currentTimeMillis() / 1000;
        if (cooldown == 0 || time + cooldown < currentTime) {
            return 0;
        } else {
            long timeleft = time + cooldown - currentTime;
            return timeleft;
        }
    }
}

