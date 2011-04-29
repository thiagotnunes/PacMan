package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.Collidable;
import com.pacman.geometry.Point;
import com.pacman.geometry.SquarePolygon;
import com.pacman.renderer.Renderable;

public class Board implements Renderable, Collidable {

	private final TiledMap map;
	private List<Tile> collidableBlocks;

	protected Board(TiledMap map, List<Tile> collidableBlocks) {
		this.map = map;
		this.collidableBlocks = collidableBlocks;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < map.getLayerCount(); i++) {
			if (isLayerVisible(i)) {
				map.render(0, 0, i);
			}
		}
		// for (Block block : blocks) {
		// g.draw(block.getPolygon());
		// }
	}

	@Override
	public Point getPosition() {
		return new Point(0f, 0f);
	}

	@Override
	public boolean isCollidingWith(SquarePolygon collidable) {
		for (Tile block : collidableBlocks) {
			if (block.isCollidingWith(collidable)) {
				return true;
			}
		}
		return false;
	}

	private Boolean isLayerVisible(int layerIndex) {
		return Boolean.valueOf(map.getLayerProperty(layerIndex, VISIBLE.property(),
				VISIBLE.defaultValue()));
	}

}
