package com.pacman.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageFactory {

	public Image create(String path) throws SlickException {
		return new Image(path);
	}

}
