package com.pacman.renderer;

import org.lwjgl.util.Point;

public interface Renderable {

	Point getPosition();

	void draw();

}
