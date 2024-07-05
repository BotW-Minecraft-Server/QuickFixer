package link.botwmcs.quickfixer.mixin.reesessodiumptions;

import link.botwmcs.quickfixer.config.CommonConfig;
import me.flashyreese.mods.reeses_sodium_options.client.gui.SodiumVideoOptionsScreen;
import me.jellysquid.mods.sodium.client.SodiumClientMod;
import me.jellysquid.mods.sodium.client.data.fingerprint.HashedFingerprint;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.prompt.ScreenPrompt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mixin(value = SodiumVideoOptionsScreen.class, remap = false)
public class SodiumVideoOptionsScreenMixin {
    @Shadow private void openDonationPrompt(SodiumGameOptions options) {}

    /**
     * @author Sam_Chai
     * @reason Remove donation prompt
     */
    @Overwrite
    private void checkPromptTimers() {

        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
            SodiumGameOptions options = SodiumClientMod.options();
            if (!options.notifications.hasSeenDonationPrompt) {
                HashedFingerprint fingerprint = null;

                try {
                    fingerprint = HashedFingerprint.loadFromDisk();
                } catch (Throwable var5) {
                    Throwable t = var5;
                    SodiumClientMod.logger().error("Failed to read the fingerprint from disk", t);
                }

                if (fingerprint != null) {
                    Instant now = Instant.now();
                    Instant threshold = Instant.ofEpochSecond(fingerprint.timestamp()).plus(3L, ChronoUnit.DAYS);
                    if (now.isAfter(threshold)) {
                        if (CommonConfig.CONFIG.enableSodiumTweak.get()) {
                            options.notifications.hasSeenDonationPrompt = true;
                        } else {
                            openDonationPrompt(options);
                        }

                    }

                }
            }
        }
    }


}
