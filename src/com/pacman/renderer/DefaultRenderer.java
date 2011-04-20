package com.pacman.renderer;

import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;

public class DefaultRenderer implements Renderer {

	@Override
	public void render(Renderable renderable, Graphics g) {
		Point position = renderable.getPosition();
		draw(renderable, g, position.getX(), position.getY());
	}

	private void draw(Renderable renderable, Graphics g, Integer x, Integer y) {
		g.pushTransform();
		g.translate(x, y);
		renderable.draw(g);
		g.popTransform();
	}

}
