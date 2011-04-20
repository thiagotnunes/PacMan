package com.pacman.entity.maze;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.geometry.SquarePolygon;

public class BlockFactory {

	private static final String DEFAULT_COLLIDABLE_VALUE = "false";
	private static final String COLLIDABLE = "collidable";
	private static final int COLLISION_LAYER = 0;

	public List<Block> from(TiledMap map) {
		Integer blockWidth = map.getTileWidth();
		Integer width = map.getWidth();
		Integer height = map.getHeight();
		List<Block> blocks = new ArrayList<Block>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int tileId = map.getTileId(x, y, COLLISION_LAYER);
				if (isCollidable(map, tileId)) {
					blocks.add(new Block(new SquarePolygon((float) x
							* blockWidth, (float) y * blockWidth,
							(float) blockWidth)));
				}
			}
		}

		return blocks;
	}

	private Boolean isCollidable(TiledMap map, int tileId) {
		return Boolean.valueOf(map.getTileProperty(tileId, COLLIDABLE,
				DEFAULT_COLLIDABLE_VALUE));
	}

}
