package com.pacman.entity.character;

import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.pacman.entity.Direction;
import com.pacman.entity.ImageFactory;
import com.pacman.entity.PositionedImage;

public class PacManFactory {

	private static final String PACMAN_IMAGE_PATH = "data/pacman.png";
	private final ImageFactory imageFactory;

	public PacManFactory(ImageFactory imageFactory) {
		this.imageFactory = imageFactory;
	}

	public PacMan create() throws SlickException {
		Image image = imageFactory.create(PACMAN_IMAGE_PATH);
		return new PacMan(new PositionedImage(new Point(100, 100), new Dimension(20, 20), image), Direction.LEFT);
	}

}
