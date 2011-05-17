package com.pacman.graphics;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationFactory {

	public Animation from(List<String> paths, Integer delay) throws SlickException {
		Image[] frames = new Image[paths.size()];
	
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Image(paths.get(i));
		}
	
		return new Animation(frames, delay);
	}

}
