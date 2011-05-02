package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.Tile;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.geometry.Point;
import com.pacman.renderer.Renderable;

public class Board implements Renderable {

	private final TiledMap map;
	private final List<Tile> walls;
	private final List<Tile> food;

	protected Board(TiledMap map, List<Tile> walls, List<Tile> food) {
		this.map = map;
		this.walls = walls;
		this.food = food;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < map.getLayerCount(); i++) {
			if (isLayerVisible(i)) {
				map.render(0, 0, i);
			}
		}
		for(Tile tile : food) {
			g.draw(tile.getPolygon());
		}
//		for (Tile tile : walls) {
//			g.draw(tile.getPolygon());
//		}
	}

	@Override
	public Point getPosition() {
		return new Point(0f, 0f);
	}

	public boolean isCollidingWithWall(CollisionPolygon collidable) {
		return isCollidingWith(collidable, walls) != null;
	}

	private Tile isCollidingWith(CollisionPolygon collidable, List<Tile> tiles) {
		for (Tile tile : tiles) {
			if (tile.isCollidingWith(collidable)) {
				return tile;
			}
		}
		return null;
	}

	public void consume(CollisionPolygon collidable) {
		Tile tile = isCollidingWith(collidable, food);
		food.remove(tile);
	}

	private Boolean isLayerVisible(int layerIndex) {
		return Boolean.valueOf(map.getLayerProperty(layerIndex, VISIBLE
				.property(), VISIBLE.defaultValue()));
	}

}
