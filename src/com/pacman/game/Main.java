package com.pacman.game;

import org.lwjgl.util.Dimension;
import org.newdawn.slick.AppGameContainer;

import com.pacman.entity.Direction;
import com.pacman.entity.character.PacMan;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		PacMan pacMan = new PacMan(null, Direction.DOWN);

		Dimension gameDimension = new Dimension(800, 600);
		PacManGame pacManGame = new PacManGame(title, pacMan, gameDimension);

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(gameDimension.getWidth(), gameDimension
				.getHeight(), false);

		container.start();
	}

}
