package com.pacman.game;

import org.lwjgl.util.Dimension;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.AnimationFactory;
import com.pacman.entity.Board;
import com.pacman.entity.character.PacManFactory;
import com.pacman.renderer.DefaultRenderer;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		Dimension gameDimension = new Dimension(800, 600);
		DefaultRenderer renderer = new DefaultRenderer();
		TiledMap map = new TiledMap("data/maze/1/1.tmx");
		
		Board board = new Board(map, gameDimension, renderer);
		PacManGame pacManGame = new PacManGame(title, new PacManFactory(
				new AnimationFactory()), board, renderer);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(gameDimension.getWidth(), gameDimension
				.getHeight(), false);

		container.start();
	}

}
