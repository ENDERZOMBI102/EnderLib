package com.enderzombi102.enderlib;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Images {
	/**
	 * Function used to rotate a {@link BufferedImage} by $ANGLE degrees
	 * @param img image to rotate
	 * @param degrees amount of degrees to rotate the image by
	 */
	@Contract( value = "_,_ -> new", pure = true )
	public static @NotNull BufferedImage rotateImage( @NotNull BufferedImage img, double degrees ) {
		// find necessary values
		double rads = Math.toRadians( degrees );
		double sin = Math.abs( Math.sin( rads ) );
		double cos = Math.abs( Math.cos( rads ) );
		int rotWidth = (int) Math.floor( img.getWidth() * cos + img.getHeight() * sin );
		int rotHeight = (int) Math.floor( img.getHeight() * cos + img.getWidth() * sin );
		// create new image and its Graphics2D object
		var rotated = new BufferedImage( rotWidth, rotHeight, BufferedImage.TYPE_4BYTE_ABGR );
		var graph = rotated.createGraphics();
		// apply transform and draw image
		graph.setTransform( AffineTransform.getRotateInstance(
			rads,
			img.getWidth() / 2f,
			img.getHeight() / 2f
		));
		graph.drawImage( img, 0, 0, null );
		graph.dispose();

		return rotated;
	}
}
