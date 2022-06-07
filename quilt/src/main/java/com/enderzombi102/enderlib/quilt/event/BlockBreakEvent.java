package com.enderzombi102.enderlib.quilt.event;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.base.api.event.Event;

public interface BlockBreakEvent {
	Event<BlockBreakEvent> EVENT = Event.create(
		BlockBreakEvent.class,
		callbacks -> ( world, entity, state, pos ) -> {
			var cancelled = false;
			for ( var listener : callbacks )
				//noinspection AssignmentUsedAsCondition
				if ( cancelled = !listener.onBlockBreak( world, entity, state, pos ) )
					break;
			return cancelled;
		}
	);

	/**
	 *
	 * @return false if cancelled, true otherwise
	 */
	boolean onBlockBreak( @NotNull World world, @Nullable Entity entity, @NotNull BlockState state, @NotNull BlockPos pos );
}
