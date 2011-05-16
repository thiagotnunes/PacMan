package com.pacman.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.pacman.entity.movement.Movement;

public class AnimationFactory {

	private static final int TOTAL_MOVEMENT_FRAMES = 4;

	public Animation from(List<String> paths, Integer delay)
			throws SlickException {
		Image[] frames = new Image[paths.size()];

		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Image(paths.get(i));
		}

		return new Animation(frames, delay);
	}

	public Animation from(Movement movement, Integer delay, boolean stopped)
			throws SlickException {
		String prefix = "data" + File.separator + "pacman" + File.separator
				+ movement + File.separator + "Pacman_" + movement + "-";
		String extension = ".png";

		List<String> paths = new ArrayList<String>();
		
		if (stopped) {
			paths.add(prefix + 1 + extension);
		} else {
			for (int i = 0; i < TOTAL_MOVEMENT_FRAMES; i++) {
				paths.add(prefix + i + extension);
			}
		}

		return from(paths, delay);
	}
	

}
