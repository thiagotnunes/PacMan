package com.pacman.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundPlayer {

	private final Sound sound;

	protected SoundPlayer(Sound sound) {
		this.sound = sound;
	}

	public SoundPlayer(String path) throws SlickException {
		this(new Sound(path));
	}

	public void play() {
		if (!sound.playing()) {
			sound.play();
		}
	}

	public Boolean isPlaying() {
		return sound.playing();
	}
}