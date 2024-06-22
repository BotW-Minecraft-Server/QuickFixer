package link.botwmcs.quickfixer.mixin.rei;

import link.botwmcs.quickfixer.config.ServerConfig;
import link.botwmcs.quickfixer.impl.rei.LtsxREIConfigCategories;
import me.shedaniel.rei.impl.client.config.ConfigManagerImpl;
import me.shedaniel.rei.impl.client.gui.config.REIConfigScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ConfigManagerImpl.class, remap = false)
public class ConfigManagerImplMixin {
    /**
     * @author Sam_Chai
     * @reason Overwrite config screen
     */
    @Overwrite
    public Screen getConfigScreen(Screen parent) {
        if (ServerConfig.CONFIG.enableReiGuiTweak.get()) {
            return new REIConfigScreen(parent, LtsxREIConfigCategories.CATEGORIES);
        } else {
            return new REIConfigScreen(parent);
        }

    }
}
