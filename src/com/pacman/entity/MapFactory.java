package com.pacman.entity;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapFactory {

	public TiledMap create(String path) throws SlickException {
		return new TiledMap(path);
	}
	
}
