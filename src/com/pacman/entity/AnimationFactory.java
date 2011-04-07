package com.pacman.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationFactory {

	public Animation createFromPath(Integer delay, String[] paths) throws SlickException {
		Image[] frames = new Image[paths.length];
		
		for (int i=0; i<frames.length; i++) {
			frames[i] = new Image(paths[i]);
		}
		
		return new Animation(frames, delay);
	}
	
}
