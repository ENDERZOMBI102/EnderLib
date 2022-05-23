package EnderLib.extensions.java.awt.image.BufferedImage;

import com.enderzombi102.enderlib.Images;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

@Extension
public class BufferedImageExt {
	/**
	 * Method used to rotate this image by $ANGLE degrees
	 * @param degrees amount of degrees to rotate the image by
	 */
	@Contract( value = "_,_ -> new", pure = true )
	public static @NotNull BufferedImage rotate( @This BufferedImage thiz, double degrees ) {
		return Images.rotateImage( thiz, degrees );
	}
}