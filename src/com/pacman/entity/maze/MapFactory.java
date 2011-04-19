package com.pacman.entity.maze;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapFactory {

	public TiledMap from(String path) throws SlickException {
		return new TiledMap(path);
	}
	
}
