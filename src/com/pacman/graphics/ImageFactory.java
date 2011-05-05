package com.pacman.graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageFactory {

	public Image from(String path) throws SlickException {
		return new Image(path);
	}
	
}
