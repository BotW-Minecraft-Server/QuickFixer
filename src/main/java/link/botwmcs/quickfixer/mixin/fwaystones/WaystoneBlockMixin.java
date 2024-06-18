package link.botwmcs.quickfixer.mixin.fwaystones;

import link.botwmcs.quickfixer.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wraith.fwaystones.block.WaystoneBlock;

@Mixin(value = WaystoneBlock.class)
public class WaystoneBlockMixin {
    @Inject(method = "getDestroyProgress", at = @At("RETURN"), cancellable = true)
    private void modifyDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (ServerConfig.CONFIG.enableWaystoneCantDestroy.get()) {
            cir.setReturnValue(0.0F);
        } else {
            cir.setReturnValue(cir.getReturnValue());
        }
    }
}
