package com.pacman.entity.maze.tile;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;

public abstract class TileFactory {

	protected TileFilter filter;

	public TileFactory(TileFilter filter) {
		this.filter = filter;
	}

	public List<Tile> from(TiledMap map, Integer layer) {
		Integer width = map.getWidth();
		Integer height = map.getHeight();
		List<Tile> tiles = new ArrayList<Tile>();

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

	protected abstract Tile createTile(Integer x, Integer y, TiledMap map);

}
