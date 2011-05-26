package com.pacman.entity.maze.tile;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;

public abstract class TileFactory<T extends Tile> {

	protected TileFilter filter;

	public TileFactory(TileFilter filter) {
		this.filter = filter;
	}

	public List<T> from(TiledMap map, Integer layer) throws SlickException {
		Integer width = map.getWidth();
		Integer height = map.getHeight();
		List<T> tiles = new ArrayList<T>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Integer id = map.getTileId(x, y, layer);
				if (filter.isValid(id, map)) {
					tiles.add(createTile(x, y, map));
				}
			}
		}

		return tiles;
	}

	protected abstract T createTile(Integer x, Integer y, TiledMap map) throws SlickException;
}
