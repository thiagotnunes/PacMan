package com.pacman.game;

import org.newdawn.slick.AppGameContainer;

import com.pacman.entity.character.PacManFactory;
import com.pacman.entity.character.PacManKeyListenerFactory;
import com.pacman.entity.maze.BoardFactory;
import com.pacman.entity.maze.MapFactory;
import com.pacman.entity.maze.filter.CollidableTileFilter;
import com.pacman.entity.maze.filter.ConsumableTileFilter;
import com.pacman.entity.maze.tile.FoodTileFactory;
import com.pacman.entity.maze.tile.WallTileFactory;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.graphics.ImageFactory;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		WallTileFactory wallFactory = new WallTileFactory(
				new CollidableTileFilter());
		FoodTileFactory foodFactory = new FoodTileFactory(
				new ConsumableTileFilter(), new ImageFactory(),
				FoodTileFactory.FOOD_PATH);
		BoardFactory boardFactory = new BoardFactory(new MapFactory(),
				wallFactory, foodFactory);
		MovementBuilder movementBuilder = new MovementBuilder();
		PacManFactory pacManFactory = new PacManFactory(movementBuilder);

		PacManKeyListenerFactory listenerFactory = new PacManKeyListenerFactory(movementBuilder);

		PacManGame pacManGame = new PacManGame(title, pacManFactory,
				boardFactory, listenerFactory);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(700, 800, false);
		
		container.start();
	}

}
