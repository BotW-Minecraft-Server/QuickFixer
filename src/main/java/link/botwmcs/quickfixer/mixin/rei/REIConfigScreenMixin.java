package link.botwmcs.quickfixer.mixin.rei;

import link.botwmcs.quickfixer.config.ServerConfig;
import link.botwmcs.quickfixer.impl.rei.LtsxREIConfigCategories;
import me.shedaniel.rei.impl.client.gui.config.REIConfigScreen;
import me.shedaniel.rei.impl.client.gui.config.options.AllREIConfigCategories;
import me.shedaniel.rei.impl.client.gui.config.options.OptionCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = REIConfigScreen.class, remap = false)
public class REIConfigScreenMixin {
    @Redirect(method = "init", at = @At(
            value = "FIELD",
            target = "Lme/shedaniel/rei/impl/client/gui/config/REIConfigScreen;categories:Ljava/util/List;")
    )
    private List<OptionCategory> redirectCategories(REIConfigScreen instance) {
        if (ServerConfig.CONFIG.enableReiGuiTweak.get()) {
            return LtsxREIConfigCategories.CATEGORIES;
        } else {
            return AllREIConfigCategories.CATEGORIES;
        }

    }
}
