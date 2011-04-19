package com.pacman.entity.maze;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.geometry.SquarePolygon;

public class BlockFactory {

	private static final int COLLISION_LAYER = 0;

	public List<Block> from(TiledMap map) {
		Integer blockWidth = map.getTileWidth();
		Integer width = map.getWidth();
		Integer height = map.getHeight();
		List<Block> blocks = new ArrayList<Block>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int tileId = map.getTileId(x, y, COLLISION_LAYER);
				if (Boolean.valueOf(map.getTileProperty(
						tileId, "collidable", "false"))) {
					blocks.add(new Block(new SquarePolygon(x * blockWidth, y
							* blockWidth, blockWidth)));
				}
			}
		}

		return blocks;
	}

}
