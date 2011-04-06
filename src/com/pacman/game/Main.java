package com.pacman.game;

import org.lwjgl.util.Dimension;
import org.newdawn.slick.AppGameContainer;

import com.pacman.entity.ImageFactory;
import com.pacman.entity.character.PacManFactory;
import com.pacman.renderer.DefaultRenderer;

public class Main {

	public static void main(String[] args) throws Exception {
		String title = "--- PacMan ---";

		Dimension gameDimension = new Dimension(800, 600);
		PacManGame pacManGame = new PacManGame(title, new PacManFactory(
				new ImageFactory()), gameDimension, new DefaultRenderer());

		AppGameContainer container = new AppGameContainer(pacManGame);
		container.setDisplayMode(gameDimension.getWidth(), gameDimension
				.getHeight(), false);

		container.start();
	}

}
