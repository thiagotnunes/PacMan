package com.pacman.renderer;

import static org.mockito.Mockito.*;

import java.awt.Point;

import org.junit.Test;
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
		verify(g).translate((float) point.getX(), (float) point.getY());
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
		
		renderer.render(renderable, g);
		
		verify(g).pushTransform();
		verify(g).translate((float) point.getX(), (float) point.getY());
		verify(renderable).draw(g);
		verify(g).popTransform();
	}
	
}
