package com.pacman.entity.maze;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.FullPolygonFactory;
import com.pacman.entity.maze.filter.CollidableTileFilter;

public class BoardFactory {

	private final MapFactory mapFactory;
	private final TileFactory blockFactory;

	public BoardFactory(MapFactory mapFactory, TileFactory blockFactory) {
		this.mapFactory = mapFactory;
		this.blockFactory = blockFactory;
	}

	public Board from(String path) throws SlickException {
		TiledMap map = mapFactory.from(path);
		return new Board(map, blockFactory.from(map, 0,
				new CollidableTileFilter(), new FullPolygonFactory()));
	}

}
