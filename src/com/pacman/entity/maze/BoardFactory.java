package com.pacman.entity.maze;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.TileFactory;

public class BoardFactory {

	private final MapFactory mapFactory;
	private final TileFactory tileFactory;

	public BoardFactory(MapFactory mapFactory, TileFactory tileFactory) {
		this.mapFactory = mapFactory;
		this.tileFactory = tileFactory;
	}

	public Board from(String path) throws SlickException {
		TiledMap map = mapFactory.from(path);
		return new Board(map, tileFactory.from(map, 0));
	}
}
