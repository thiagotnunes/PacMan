package com.pacman.renderer;

import java.awt.Point;

import org.newdawn.slick.Graphics;

public class DefaultRenderer implements Renderer {

	@Override
	public void render(Renderable renderable, Graphics g) {
		Point position = renderable.getPosition();
		draw(renderable, g, (float) position.getX(), (float) position.getY());
	}

	private void draw(Renderable renderable, Graphics g, Float x, Float y) {
		g.pushTransform();
		g.translate(x, y);
		renderable.draw(g);
		g.popTransform();
	}

}
