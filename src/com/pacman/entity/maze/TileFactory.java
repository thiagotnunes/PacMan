package com.pacman.entity.maze;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.entity.maze.filter.TileFilter;

public class TileFactory {

	public List<Tile> from(TiledMap map, Integer layer, TileFilter filter,
			PolygonFactory polygonFactory) {
		Integer width = map.getWidth();
		Integer height = map.getHeight();
		Float blockWidth = (float) map.getTileWidth();
		List<Tile> tiles = new ArrayList<Tile>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int tileId = map.getTileId(x, y, layer);
				if (filter.isValid(tileId, map)) {
					tiles.add(new Tile(polygonFactory.from(x * blockWidth, y
							* blockWidth, blockWidth)));
				}
			}
		}

		return tiles;
	}

}
