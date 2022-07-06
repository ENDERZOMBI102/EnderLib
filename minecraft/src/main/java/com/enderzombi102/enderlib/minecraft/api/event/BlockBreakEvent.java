package com.enderzombi102.enderlib.minecraft.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockBreakEvent {
	Event<BlockBreakEvent> EVENT = EventFactory.createArrayBacked(
		BlockBreakEvent.class,
		callbacks -> ( entity, state, pos ) -> {
			var cancelled = false;
			for ( var listener : callbacks )
				//noinspection AssignmentUsedAsCondition
				if ( cancelled = !listener.onBlockBreak( entity, state, pos ) )
					break;
			return cancelled;
		}
	);

	/**
	 *
	 * @return false if cancelled, true otherwise
	 */
	boolean onBlockBreak( @Nullable Entity entity, @NotNull BlockState state, @NotNull BlockPos pos );
}
