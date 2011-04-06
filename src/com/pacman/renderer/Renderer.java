package com.pacman.renderer;

import org.newdawn.slick.Graphics;

public interface Renderer {

	void render(Renderable renderable, Graphics g);

	void render(Renderable renderable, Graphics g, Float speed);

}
