package link.botwmcs.quickfixer.mixin.kiwi;

import com.google.common.collect.Lists;
import link.botwmcs.quickfixer.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import snownee.kiwi.KiwiClientConfig;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntConsumer;

@Mixin(targets = "snownee.kiwi.client.TooltipEvents$DebugTooltipCache", remap = false)
public class DebugTooltipCacheMixin {
    @Shadow @Final
    private List<List<String>> pages;
    @Shadow @Final
    private List<String> pageTypes;
    @Shadow
    private boolean showTags;
    @Shadow
    private long lastShowTags;
    @Shadow
    private int pageNow;
    @Shadow
    public boolean needUpdatePreferredType;
    @Shadow
    private String preferredType;
    @Shadow
    private ItemStack itemStack;


    /**
     * @author Sam_Chai
     * @reason Remove discuss "(alt)" hint
     */
    @Overwrite
    public void appendTagsTooltip(List<Component> tooltip) {
        if (!this.pages.isEmpty()) {
            if (this.showTags && Util.getMillis() - this.lastShowTags > 60000L) {
                this.showTags = false;
            }

            if (!this.showTags) {
                if (KiwiClientConfig.tagsTooltipAppendKeybindHint) {
                    if (!ServerConfig.CONFIG.enableKiwiTweak.get()) {
                        this.findIdLine(tooltip, (i) -> {
                            tooltip.set(i, ((Component)tooltip.get(i)).copy().append(" (alt)"));
                        });
                    }
                }

            } else {
                this.lastShowTags = Util.getMillis();
                List<Component> sub = Lists.newArrayList();
                this.pageNow = Math.floorMod(this.pageNow, this.pages.size());
                if (this.needUpdatePreferredType) {
                    this.needUpdatePreferredType = false;
                    this.preferredType = (String)this.pageTypes.get(this.pageNow);
                }

                List<String> page = (List)this.pages.get(this.pageNow);
                Iterator var4 = page.iterator();

                while(var4.hasNext()) {
                    String tag = (String)var4.next();
                    sub.add(Component.literal(tag).withStyle(ChatFormatting.DARK_GRAY));
                }

                int index = this.findIdLine(tooltip, (i) -> {
                    String type = (String)this.pageTypes.get(this.pageNow);
                    tooltip.set(i, ((Component)tooltip.get(i)).copy().append(" (%s/%s...%s)".formatted(this.pageNow + 1, this.pages.size(), type)));
                });
                index = index == -1 ? tooltip.size() : index + 1;
                tooltip.addAll(index, sub);
            }
        }
    }

    @Unique
    private int findIdLine(List<Component> tooltip, IntConsumer consumer) {
        String id = BuiltInRegistries.ITEM.getKey(this.itemStack.getItem()).toString();

        for(int i = 0; i < tooltip.size(); ++i) {
            Component component = (Component)tooltip.get(i);
            if (component.getString().equals(id)) {
                consumer.accept(i);
                return i;
            }
        }
        return -1;
    }

}
