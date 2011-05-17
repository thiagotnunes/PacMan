package com.pacman.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class MovementAnimationFactory extends AnimationFactory {

	private static final int TOTAL_MOVEMENT_FRAMES = 4;

	public Animation from(String name, Integer delay)
			throws SlickException {
		String prefix = "data" + File.separator + "pacman" + File.separator
				+ name + File.separator + "Pacman_" + name + "-";
		String extension = ".png";

		List<String> paths = new ArrayList<String>();

		for (int i = 0; i < TOTAL_MOVEMENT_FRAMES; i++) {
			paths.add(prefix + i + extension);
		}

		return from(paths, delay);
	}

}
