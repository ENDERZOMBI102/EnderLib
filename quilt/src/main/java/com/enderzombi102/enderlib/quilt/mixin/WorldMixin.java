package com.enderzombi102.enderlib.quilt.mixin;

import com.enderzombi102.enderlib.quilt.event.BlockBreakEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(World.class)
public abstract class WorldMixin {
	@Shadow public abstract BlockState getBlockState( BlockPos pos );

	@Inject(
		method = "breakBlock",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;"
		),
		locals = LocalCapture.CAPTURE_FAILHARD,
		cancellable = true
	)
	public void onBreak( BlockPos pos, boolean drop, Entity breakingEntity, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir, BlockState state ) {
		if (!
			BlockBreakEvent.EVENT.invoker().onBlockBreak(
				(World) (Object) this,
				breakingEntity,
				state,
				pos
			)
		) {
			cir.setReturnValue( false );
			cir.cancel();
		}
	}
}
