package com.pacman.entity;

import static com.pacman.entity.Direction.DOWN;
import static com.pacman.entity.Direction.LEFT;
import static com.pacman.entity.Direction.RIGHT;
import static com.pacman.entity.Direction.UP;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Image;

import com.pacman.renderer.Renderable;

public class PositionedImageTest {

	@Test
	public void shouldDrawImage() throws Exception {
		Image image = mock(Image.class);
		ReadablePoint position = mock(ReadablePoint.class);
		ReadableDimension dimension = mock(ReadableDimension.class);
		Renderable positionedImage = new PositionedImage(position, dimension, image);
		positionedImage.draw();

		verify(image).draw(0, 0, dimension.getWidth(), dimension.getWidth());
	}

	@Test
	public void shouldUpdatePosition() throws Exception {
		validateUpdatePosition(UP, 0, -1);
		validateUpdatePosition(DOWN, 0, 1);
		validateUpdatePosition(RIGHT, 1, 0);
		validateUpdatePosition(LEFT, -1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForIllegalDirection() throws Exception {
		validateUpdatePosition(null, 0, 0);
	}

	private void validateUpdatePosition(Direction direction, int x, int y) {
		int currentX = 0;
		int currentY = 0;
		Rectangle rectangle = new Rectangle(currentX, currentY, 100, 100);
		PositionedImage image = new PositionedImage(rectangle, null);

		image.move(direction, 1, 1);

		assertEquals(currentX + x, rectangle.getX());
		assertEquals(currentY + y, rectangle.getY());
	}
}
