package com.pacman.entity.character;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.Image;

import com.pacman.entity.ImageFactory;

public class PacManFactoryTest {

	@Test
	public void shouldCreateAPacManInstance() throws Exception {
		ImageFactory imageFactory = mock(ImageFactory.class);
		Image image = mock(Image.class);
		String imagePath = "data/pacman.png";
		when(imageFactory.create(imagePath)).thenReturn(image);
		
		PacMan pacMan = new PacManFactory(imageFactory).create();
		
		verify(imageFactory).create(imagePath);
		assertNotNull(pacMan);
	}
	
}
