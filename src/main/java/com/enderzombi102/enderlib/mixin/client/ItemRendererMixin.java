package com.enderzombi102.enderlib.mixin.client;

import com.enderzombi102.enderlib.Config;
import com.enderzombi102.enderlib.common.RomanHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

	@Shadow
	public float zOffset;

	/**
	 * Renders the overlay for items in GUIs, including the damage bar and the item count.
	 *
	 * @param countLabel a label for the stack; if null, the stack count is drawn instead
	 * @author ENDERZOMBI102
	 */
	@Overwrite
	public void renderGuiItemOverlay(TextRenderer renderer, ItemStack stack, int x, int y, @Nullable String countLabel) {
		if (!stack.isEmpty()) {
			MatrixStack matrixStack = new MatrixStack();
			if (stack.getCount() != 1 || countLabel != null) {
				String string;
				int count = stack.getCount();
				// use the better things
				if (Config.isUsingRomanNumbers()) {
					string = RomanHelper.toRoman( count );
				} else {
					if ( countLabel == null ) {
						if ( Config.getRadix() != 0 && Config.getRadix() != 10 ) {
							string = Integer.toString(count, 16 );

						} else {
							string = "" + count;
						}
					} else {
						string = countLabel;
					}
				}
				matrixStack.translate(0.0D, 0.0D, this.zOffset + 200.0F );
				VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate( Tessellator.getInstance().getBuffer() );
				renderer.draw(
					string,
					x + 19 - 2 - renderer.getWidth(string),
					y + 6 + 3,
					16777215,
					true,
					matrixStack.peek().getModel(),
					immediate,
					false,
					0,
					15728880
				);
				immediate.draw();
			}

			if (stack.isDamaged()) {
				RenderSystem.disableDepthTest();
				RenderSystem.disableTexture();
				RenderSystem.disableAlphaTest();
				RenderSystem.disableBlend();
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferBuilder = tessellator.getBuffer();
				float f = (float)stack.getDamage();
				float g = (float)stack.getMaxDamage();
				float h = Math.max(0.0F, (g - f) / g);
				int i = Math.round(13.0F - f * 13.0F / g);
				int j = MathHelper.hsvToRgb(h / 3.0F, 1.0F, 1.0F);
				this.renderGuiQuad(bufferBuilder, x + 2, y + 13, 13, 2, 0, 0, 0, 255);
				this.renderGuiQuad(bufferBuilder, x + 2, y + 13, i, 1, j >> 16 & 255, j >> 8 & 255, j & 255, 255);
				RenderSystem.enableBlend();
				RenderSystem.enableAlphaTest();
				RenderSystem.enableTexture();
				RenderSystem.enableDepthTest();
			}

			ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
			float k = clientPlayerEntity == null ? 0.0F : clientPlayerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), MinecraftClient.getInstance().getTickDelta());
			if (k > 0.0F) {
				RenderSystem.disableDepthTest();
				RenderSystem.disableTexture();
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				Tessellator tessellator2 = Tessellator.getInstance();
				BufferBuilder bufferBuilder2 = tessellator2.getBuffer();
				this.renderGuiQuad(bufferBuilder2, x, y + MathHelper.floor(16.0F * (1.0F - k)), 16, MathHelper.ceil(16.0F * k), 255, 255, 255, 127);
				RenderSystem.enableTexture();
				RenderSystem.enableDepthTest();
			}

		}
	}

	@Redirect(
			method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;getWidth(Ljava/lang/String;)I")
	)
	private int width(TextRenderer renderer, String text) {
		return (int) ( renderer.getWidth(text) * RomanHelper.scale(text) );
	}

	@Shadow
	private void renderGuiQuad(BufferBuilder bufferBuilder2, int x, int i, int i1, int ceil, int i2, int i3, int i4, int i5) {}

}
