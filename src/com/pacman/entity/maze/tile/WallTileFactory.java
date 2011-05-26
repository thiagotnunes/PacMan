package com.pacman.entity.maze.tile;

import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.filter.TileFilter;
import com.pacman.geometry.CollisionPolygon;

public class WallTileFactory extends TileFactory<Tile> {

	public WallTileFactory(TileFilter filter) {
		super(filter);
	}

	@Override
	protected Tile createTile(Integer x, Integer y, TiledMap map) {
		Float width = (float) map.getTileWidth();
		CollisionPolygon collisionPolygon = new CollisionPolygon(x * width, y
				* width, width);
		return new Tile(collisionPolygon);
	}
}
