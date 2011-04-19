package com.pacman.entity.maze;

import java.util.List;

import org.lwjgl.util.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.Collidable;
import com.pacman.renderer.Renderable;

public class Board implements Renderable, Collidable {

	private final TiledMap map;
	private List<Block> blocks;

	protected Board(TiledMap map, List<Block> blocks) {
		this.map = map;
		this.blocks = blocks;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	@Override
	public void draw(Graphics g) {
		map.render(0, 0);
	}

	@Override
	public Point getPosition() {
		return new Point(0, 0);
	}

	@Override
	public boolean isCollidingWith(Shape shape) {
		for (Block block : blocks) {
			if (block.isCollidingWith(shape)) {
				return true;
			}
		}
		return false;
	}

}
