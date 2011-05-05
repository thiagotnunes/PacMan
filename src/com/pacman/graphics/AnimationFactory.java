package com.pacman.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationFactory {

	private static final int TOTAL_FRAMES = 4;

	public Animation from(List<String> paths, Integer delay)
			throws SlickException {
		Image[] frames = new Image[paths.size()];

		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Image(paths.get(i));
		}

		return new Animation(frames, delay);
	}

	public Animation from(String direction, Integer delay)
			throws SlickException {
		String prefix = "data" + File.separator + "pacman" + File.separator
				+ direction + File.separator + "Pacman_" + direction + "-";
		String extension = ".png";

		List<String> paths = new ArrayList<String>();
		for (int i = 0; i < TOTAL_FRAMES; i++) {
			paths.add(prefix + i + extension);
		}

		return from(paths, delay);
	}

}
