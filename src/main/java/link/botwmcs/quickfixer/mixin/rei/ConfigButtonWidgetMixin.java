package link.botwmcs.quickfixer.mixin.rei;

import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.config.ConfigManager;
import me.shedaniel.rei.api.client.favorites.FavoriteMenuEntry;
import me.shedaniel.rei.api.client.gui.config.DisplayPanelLocation;
import me.shedaniel.rei.api.client.gui.config.SyntaxHighlightingMode;
import me.shedaniel.rei.impl.client.config.ConfigManagerImpl;
import me.shedaniel.rei.impl.client.config.ConfigObjectImpl;
import me.shedaniel.rei.impl.client.gui.modules.entries.*;
import me.shedaniel.rei.impl.client.gui.widget.ConfigButtonWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

@Mixin(value = ConfigButtonWidget.class, remap = false)
public class ConfigButtonWidgetMixin {
    /**
     * @author Sam_Chai
     * @reason Remove cheat toggle and other useless options
     */
    @Overwrite
    private static Collection<FavoriteMenuEntry> menuEntries() {
        ConfigObjectImpl config = ConfigManagerImpl.getInstance().getConfig();
//        MutableComponent var10000 = Component.translatable("text.rei.cheating");
//        Objects.requireNonNull(config);
//        BooleanSupplier var10001 = config::isCheating;
//        Objects.requireNonNull(config);
//        ToggleMenuEntry var9 = ToggleMenuEntry.of(var10000, var10001, config::setCheating);
//        EmptyMenuEntry var10 = new EmptyMenuEntry(4);
//        TextMenuEntry var10002 = new TextMenuEntry(() -> {
//            if (!ClientHelper.getInstance().isCheating()) {
//                return Component.translatable("text.rei.cheating_disabled");
//            } else if (!ClientHelperImpl.getInstance().hasOperatorPermission()) {
//                return Minecraft.getInstance().gameMode.hasInfiniteItems() ? Component.translatable("text.rei.cheating_limited_creative_enabled") : Component.translatable("text.rei.cheating_enabled_no_perms");
//            } else {
//                return ClientHelperImpl.getInstance().hasPermissionToUsePackets() ? Component.translatable("text.rei.cheating_enabled") : Component.translatable("text.rei.cheating_limited_enabled");
//            }
//        });
//        SeparatorMenuEntry var10003 = new SeparatorMenuEntry();
        MutableComponent var10004 = Component.translatable("text.rei.config.menu.dark_theme");
        Objects.requireNonNull(config);
        ToggleMenuEntry var11 = ToggleMenuEntry.ofDeciding(var10004, config::isUsingDarkTheme, (dark) -> {
            config.setUsingDarkTheme(dark);
            return false;
        });
//        MutableComponent var10005 = Component.translatable("text.rei.config.menu.reduced_motion");
//        Objects.requireNonNull(config);
//        BooleanSupplier var10006 = config::isReducedMotion;
//        Objects.requireNonNull(config);
//        ToggleMenuEntry var1 = ToggleMenuEntry.of(var10005, var10006, config::setReducedMotion);
        MutableComponent var2 = Component.translatable("text.rei.config.menu.craftable_filter");
        Objects.requireNonNull(config);
        BooleanSupplier var10007 = config::isCraftableFilterEnabled;
        Objects.requireNonNull(config);
        ToggleMenuEntry var3 = ToggleMenuEntry.of(var2, var10007, config::setCraftableFilterEnabled);
        MutableComponent var10009 = Component.translatable("text.rei.config.menu.display");
        MutableComponent var10010 = Component.translatable("text.rei.config.menu.display.remove_recipe_book");
        Objects.requireNonNull(config);
        ToggleMenuEntry var4 = ToggleMenuEntry.of(var10010, config::doesDisableRecipeBook, (disableRecipeBook) -> {
            config.setDisableRecipeBook(disableRecipeBook);
            Screen screen = Minecraft.getInstance().screen;
            if (screen != null) {
                screen.init(Minecraft.getInstance(), screen.width, screen.height);
            }

        });
        MutableComponent var10011 = Component.translatable("text.rei.config.menu.display.left_side_mob_effects");
        Objects.requireNonNull(config);
        ToggleMenuEntry var5 = ToggleMenuEntry.of(var10011, config::isLeftSideMobEffects, (disableRecipeBook) -> {
            config.setLeftSideMobEffects(disableRecipeBook);
            Screen screen = Minecraft.getInstance().screen;
            if (screen != null) {
                screen.init(Minecraft.getInstance(), screen.width, screen.height);
            }

        });
        MutableComponent var10012 = Component.translatable("text.rei.config.menu.display.left_side_panel");
        Objects.requireNonNull(config);
        ToggleMenuEntry var6 = ToggleMenuEntry.of(var10012, config::isLeftHandSidePanel, (bool) -> {
            config.setDisplayPanelLocation(bool ? DisplayPanelLocation.LEFT : DisplayPanelLocation.RIGHT);
        });
        MutableComponent var10013 = Component.translatable("text.rei.config.menu.display.scrolling_side_panel");
        Objects.requireNonNull(config);
        BooleanSupplier var10014 = config::isEntryListWidgetScrolled;
        Objects.requireNonNull(config);
        ToggleMenuEntry var7 = ToggleMenuEntry.of(var10013, var10014, config::setEntryListWidgetScrolled);
        SeparatorMenuEntry var8 = new SeparatorMenuEntry();
        MutableComponent var10015 = Component.translatable("text.rei.config.menu.display.caching_entry_rendering");
        Objects.requireNonNull(config);
        BooleanSupplier var10016 = config::doesCacheEntryRendering;
        Objects.requireNonNull(config);
        return List.of(var11, var3, new SubMenuEntry(var10009, List.of(var4, var5, var6, var7, var8, ToggleMenuEntry.of(var10015, var10016, config::setDoesCacheEntryRendering), new SeparatorMenuEntry(), ToggleMenuEntry.of(Component.translatable("text.rei.config.menu.display.syntax_highlighting"), () -> {
            return config.getSyntaxHighlightingMode() == SyntaxHighlightingMode.COLORFUL || config.getSyntaxHighlightingMode() == SyntaxHighlightingMode.COLORFUL_UNDERSCORED;
        }, (bool) -> {
            config.setSyntaxHighlightingMode(bool ? SyntaxHighlightingMode.COLORFUL : SyntaxHighlightingMode.PLAIN_UNDERSCORED);
        }))), new SeparatorMenuEntry(), ToggleMenuEntry.ofDeciding(Component.translatable("text.rei.config.menu.config"), () -> {
            return false;
        }, ($) -> {
            ConfigManager.getInstance().openConfigScreen(REIRuntime.getInstance().getPreviousScreen());
            return false;
        }));
    }
}
