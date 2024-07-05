package link.botwmcs.quickfixer.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final CommonConfig CONFIG;

    public final ForgeConfigSpec.BooleanValue enableWaystoneCantDestroy;
    public final ForgeConfigSpec.BooleanValue enableWaystoneKanjiWaypoint;

    public final ForgeConfigSpec.BooleanValue enableReiGuiTweak;
    public final ForgeConfigSpec.BooleanValue enableCreateReiCompatible;

    public final ForgeConfigSpec.BooleanValue enableKiwiTweak;

    public final ForgeConfigSpec.BooleanValue enableSodiumTweak;

    static {
        Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("waystone");
        enableWaystoneCantDestroy = builder
                .comment("If true, players will not be able to destroy waystones.")
                .define("enableWaystoneCantDestroy", true);
        enableWaystoneKanjiWaypoint = builder
                .comment("If true, waystones will display kanji waypoints.")
                .define("enableWaystoneKanjiWaypoint", true);
        builder.pop();

        builder.push("rei");
        enableReiGuiTweak = builder
                .comment("If true, REI GUI will be tweaked.")
                .define("enableReiGuiTweak", true);
        enableCreateReiCompatible = builder
                .comment("If true, REI will be compatible with Create.")
                .define("enableCreateReiCompatible", true);
        builder.pop();

        builder.push("kiwi");
        enableKiwiTweak = builder
                .comment("If true, Kiwi will be tweaked.")
                .define("enableKiwiTweak", true);
        builder.pop();

        builder.push("sodium");
        enableSodiumTweak = builder
                .comment("If true, Sodium will be tweaked.")
                .define("enableSodiumTweak", true);
        builder.pop();
    }
}
