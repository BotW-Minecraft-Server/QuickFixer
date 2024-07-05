package link.botwmcs.quickfixer.mixin.fwaystones;

import link.botwmcs.quickfixer.config.CommonConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wraith.fwaystones.util.Utils;

import java.util.Random;

@Mixin(value = Utils.class, remap = false)
public class UtilsMixin {
    /**
     * @author Sam_Chai
     * @reason DeatHunter was here
     */
    @Inject(method = "generateUniqueId", at = @At("RETURN"), cancellable = true)
    private static void modifyGenerateUniqueId(CallbackInfoReturnable<String> cir) {
        if (CommonConfig.CONFIG.enableWaystoneKanjiWaypoint.get()) {
            Random random = new Random();
            if (random.nextDouble() < 1.0E-4) {
                cir.setReturnValue("DeatHunter was here");
            } else {
                String[] noun = {
                        "天际", "星辰", "玄武", "飞霞", "云海", "烈风", "流星", "幻影", "星辰",
                        "凤翔", "星河", "穹顶", "风暴", "雾隐", "破晓", "蓝天", "赤焰", "流云",
                        "暮光", "追风", "幻月", "逐日", "星翼", "雷霆", "宇宙", "云端", "翔龙",
                        "冰川", "雾岚", "天鹰", "星辉", "银翼", "龙腾", "冥王", "天狼", "苍龙",
                        "星轨", "风翼", "星空", "逐光", "蓝翼", "虹霓", "星语", "星光", "飞羽",
                        "极光", "星宿", "梦翼", "天穹", "星辰"
                };
                String[] verb = {
                        "守护", "彼岸", "踪迹", "归宿", "边界", "呼唤", "尾迹", "交汇",
                        "召唤", "路径", "深处", "辉光", "中心", "迷踪", "曙光", "尽头",
                        "狂舞", "足迹", "余晖", "旅程", "轨迹", "征途", "庇护", "怒吼",
                        "尽头", "幻象", "盘旋", "心脏", "隐秘", "猎场", "绽放", "闪耀",
                        "奇迹", "王座", "哀嚎", "庇护", "旅行", "疾行", "寂静", "辉煌",
                        "遨游", "奇观", "秘密", "指引", "歌唱", "梦境", "传说", "幻想",
                        "荣耀", "传送门"
                };
                String nounString = noun[random.nextInt(noun.length)];
                String verbString = verb[random.nextInt(verb.length)];
                String combined = nounString + "的" + verbString;
                cir.setReturnValue(combined);
            }
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }
}
