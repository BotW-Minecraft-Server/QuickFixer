package link.botwmcs.quickfixer.mixin.kiwi;

import link.botwmcs.quickfixer.config.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import snownee.kiwi.KiwiClientConfig;
import snownee.kiwi.client.TooltipEvents;
import snownee.kiwi.config.KiwiConfigManager;

@Mixin(value = TooltipEvents.class, remap = false)
public class TooltipEventsMixin {
    @Shadow
    private static boolean firstSeenDebugTooltip;

    /**
     * @author Sam_Chai
     * @reason Remove Kiwi debug message when first join world
     */
    @Overwrite
    private static void trySendTipMsg(Minecraft mc) {
        if (!CommonConfig.CONFIG.enableKiwiTweak.get()) {
            if (firstSeenDebugTooltip && mc.player != null) {
                firstSeenDebugTooltip = false;
                if (KiwiClientConfig.debugTooltipMsg) {
                    MutableComponent clickHere = Component.translatable("tip.kiwi.click_here").withStyle(($) -> {
                        return $.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "@kiwi disable debugTooltip"));
                    });
                    mc.player.sendSystemMessage(Component.translatable("tip.kiwi.debug_tooltip", new Object[]{clickHere.withStyle(ChatFormatting.AQUA)}));
                    KiwiClientConfig.debugTooltipMsg = false;
                    KiwiConfigManager.getHandler(KiwiClientConfig.class).save();
                }
            }
        }
    }

}
