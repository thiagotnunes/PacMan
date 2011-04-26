package com.pacman.renderer;

import org.newdawn.slick.Graphics;

import com.pacman.entity.Point;

public interface Renderable {

	Point getPosition();

	void draw(Graphics g);

}
