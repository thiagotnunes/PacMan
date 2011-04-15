package com.pacman.renderer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;


public class DefaultRendererTest {

	@Test
	public void shouldTranslateObjectBeforeRendering() throws Exception {
		Graphics g = mock(Graphics.class);
		Renderer renderer = new DefaultRenderer();
		Renderable renderable = mock(Renderable.class);
		Point point = new Point(10, 10);
		
		when(renderable.getPosition()).thenReturn(point);
		
		renderer.render(renderable, g);
		
		verify(g).pushTransform();
		verify(g).translate(point.getX(), point.getY());
		verify(renderable).draw(g);
		verify(g).popTransform();
	}
	
	@Test
	public void shouldTranslateObjectWithGivenSpeedBeforeRendering() throws Exception {
		Graphics g = mock(Graphics.class);
		Renderer renderer = new DefaultRenderer();
		Renderable renderable = mock(Renderable.class);
		Point point = new Point(10, 10);

		when(renderable.getPosition()).thenReturn(point);
		
		float speed = 0.4f;
		renderer.render(renderable, g, speed);
		
		verify(g).pushTransform();
		verify(g).translate(point.getX()*speed, point.getY()*speed);
		verify(renderable).draw(g);
		verify(g).popTransform();
	}
	
}
