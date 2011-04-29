package com.pacman.renderer;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Graphics;

import com.pacman.geometry.Point;

public class DefaultRendererTest {

	@Test
	public void shouldTranslateObjectBeforeRendering() throws Exception {
		Graphics g = mock(Graphics.class);
		Renderer renderer = new DefaultRenderer();
		Renderable renderable = mock(Renderable.class);
		Point point = new Point(10f, 10f);

		when(renderable.getPosition()).thenReturn(point);

		renderer.render(renderable, g);

		verify(g).pushTransform();
		verify(g).translate(point.getX(), point.getY());
		verify(renderable).draw(g);
		verify(g).popTransform();
	}

	@Test
	public void shouldTranslateObjectWithGivenSpeedBeforeRendering()
			throws Exception {
		Graphics g = mock(Graphics.class);
		Renderer renderer = new DefaultRenderer();
		Renderable renderable = mock(Renderable.class);
		Point point = new Point(10f, 10f);

		when(renderable.getPosition()).thenReturn(point);

		renderer.render(renderable, g);

		verify(g).pushTransform();
		verify(g).translate(point.getX(), point.getY());
		verify(renderable).draw(g);
		verify(g).popTransform();
	}

}
