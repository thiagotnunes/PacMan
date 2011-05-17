package com.pacman.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class StoppedAnimationFactory extends MovementAnimationFactory {

	private static final int STOPPED_FRAME = 1;

	@Override
	public Animation from(String name, Integer delay)
			throws SlickException {
		String prefix = "data" + File.separator + "pacman" + File.separator
				+ name + File.separator + "Pacman_" + name + "-";
		String extension = ".png";

		List<String> paths = new ArrayList<String>();

		paths.add(prefix + STOPPED_FRAME + extension);

		return from(paths, delay);
	}
}
