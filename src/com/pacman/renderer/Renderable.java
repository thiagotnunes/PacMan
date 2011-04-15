package com.pacman.renderer;

import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;

public interface Renderable {

	Point getPosition();

	void draw(Graphics g);

}
