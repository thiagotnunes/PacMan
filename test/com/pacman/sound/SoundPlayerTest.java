package com.pacman.sound;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Sound;

public class SoundPlayerTest {

	@Test
	public void shouldPlayIfItIsNotPlayingAlready() throws Exception {
		Sound sound = mock(Sound.class);
		SoundPlayer soundPlayer = new SoundPlayer(sound);
		
		when(sound.playing()).thenReturn(false);
		
		soundPlayer.play();
		
		verify(sound).play();
	}
	
	@Test
	public void shouldNotPlayIfItIsPlayingAlready() throws Exception {
		Sound sound = mock(Sound.class);
		SoundPlayer soundPlayer = new SoundPlayer(sound);
		
		when(sound.playing()).thenReturn(true);
		
		soundPlayer.play();
		
		verify(sound, never()).play();
	}
	
}
