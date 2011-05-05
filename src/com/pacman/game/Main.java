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
import com.pacman.entity.maze.tile.FoodTileFactory;
import com.pacman.entity.maze.tile.WallTileFactory;
import com.pacman.renderer.DefaultRenderer;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		DefaultRenderer renderer = new DefaultRenderer();
		FullPolygonFactory polygonFactory = new FullPolygonFactory();
		WallTileFactory wallFactory = new WallTileFactory(new CollidableTileFilter(),
				polygonFactory);
		FoodTileFactory foodFactory = new FoodTileFactory(new ConsumableTileFilter(), polygonFactory);
		BoardFactory boardFactory = new BoardFactory(new MapFactory(),
				wallFactory, foodFactory);
		PacManFactory pacManFactory = new PacManFactory();

		DirectionBuilder directionBuilder = new DirectionBuilder();
		PacManKeyListenerFactory listenerFactory = new PacManKeyListenerFactory();
		
		PacManGame pacManGame = new PacManGame(title, pacManFactory,
				boardFactory, renderer, listenerFactory, directionBuilder);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(700, 800, false);

		container.start();
	}

}
