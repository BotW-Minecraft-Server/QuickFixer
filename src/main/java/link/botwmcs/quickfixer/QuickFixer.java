package link.botwmcs.quickfixer;

import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import link.botwmcs.quickfixer.config.CommonConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

public class QuickFixer implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "quickfixer";

    @Override
    public void onInitialize() {
        loadConfig();
    }

    private void loadConfig() {
        ForgeConfigRegistry.INSTANCE.register(QuickFixer.MODID, ModConfig.Type.COMMON, CommonConfig.CONFIG_SPEC);
    }
}
