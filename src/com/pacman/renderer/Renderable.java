package com.pacman.renderer;

import org.newdawn.slick.Graphics;

import com.pacman.geometry.Point;

public interface Renderable {

	Point getPosition();

	void draw(Graphics g);

}
