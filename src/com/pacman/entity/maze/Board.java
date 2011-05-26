package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.consumable.Consumables;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.Drawable;

public class Board implements Drawable {

	private final TiledMap map;
	private final List<Tile> walls;
	private final Consumables consumables;

	public Board(TiledMap map, List<Tile> walls, Consumables consumables) {
		this.map = map;
		this.walls = walls;
		this.consumables = consumables;
	}

	@Override
	public void draw(Graphics g) {
		drawMap();
		consumables.draw(g);
	}

	private void drawMap() {
		for (int i = 0; i < map.getLayerCount(); i++) {
			if (isLayerVisible(i)) {
				map.render(0, 0, i);
			}
		}
	}

	public boolean isCollidingWithWall(CollisionPolygon collidable) {
		return collidingTile(collidable, walls) != null;
	}

	public void consume(CollisionPolygon collidable) {
		consumables.consume(collidable);
	}

	private Tile collidingTile(CollisionPolygon collidable, List<Tile> tiles) {
		for (Tile tile : tiles) {
			if (tile.isCollidingWith(collidable)) {
				return tile;
			}
		}
		return null;
	}

	private Boolean isLayerVisible(int layerIndex) {
		return Boolean.valueOf(map.getLayerProperty(layerIndex, VISIBLE
				.property(), VISIBLE.defaultValue()));
	}

}
