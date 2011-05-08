package com.pacman.game;

import org.newdawn.slick.AppGameContainer;

import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.character.PacManKeyListenerFactory;
import com.pacman.entity.collision.FullPolygonFactory;
import com.pacman.entity.direction.DirectionBuilder;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.entity.maze.MapFactory;
import com.pacman.entity.maze.filter.CollidableTileFilter;
import com.pacman.entity.maze.filter.ConsumableTileFilter;
import com.pacman.entity.maze.tile.ImageTileFactory;
import com.pacman.entity.maze.tile.WallTileFactory;
import com.pacman.graphics.ImageFactory;
import com.pacman.renderer.DefaultRenderer;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		DefaultRenderer renderer = new DefaultRenderer();
		FullPolygonFactory polygonFactory = new FullPolygonFactory();
		WallTileFactory wallFactory = new WallTileFactory(
				new CollidableTileFilter(), polygonFactory);
		ImageTileFactory foodFactory = new ImageTileFactory(
				new ConsumableTileFilter(), polygonFactory, new ImageFactory(),
				ImageTileFactory.FOOD_PATH);
		BoardFactory boardFactory = new BoardFactory(new MapFactory(),
				wallFactory, foodFactory);
		DirectionBuilder directionBuilder = new DirectionBuilder();
		PacManFactory pacManFactory = new PacManFactory(directionBuilder);

		PacManKeyListenerFactory listenerFactory = new PacManKeyListenerFactory(directionBuilder);

		PacManGame pacManGame = new PacManGame(title, pacManFactory,
				boardFactory, renderer, listenerFactory);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(700, 800, false);
		
		container.start();
	}

}
