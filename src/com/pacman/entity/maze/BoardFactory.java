package com.pacman.entity.maze;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BoardFactory {

	private final MapFactory mapFactory;
	private final BlockFactory blockFactory;

	public BoardFactory(MapFactory mapFactory, BlockFactory blockFactory) {
		this.mapFactory = mapFactory;
		this.blockFactory = blockFactory;
	}

	public Board from(String path) throws SlickException {
		TiledMap map = mapFactory.from(path);
		return new Board(map, blockFactory.from(map));
	}

}
