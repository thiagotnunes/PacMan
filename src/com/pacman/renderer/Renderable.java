package com.pacman.renderer;

import java.awt.Point;

import org.newdawn.slick.Graphics;

public interface Renderable {

	Point getPosition();

	void draw(Graphics g);

}
