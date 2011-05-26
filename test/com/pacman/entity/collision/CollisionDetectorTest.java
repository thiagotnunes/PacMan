package com.pacman.entity.collision;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pacman.geometry.CollisionPolygon;


public class CollisionDetectorTest {

	@Test
	public void shouldReturnFirstCollidableFromCollision() throws Exception {
		List<Collidable> collidables = new ArrayList<Collidable>();
		Collidable firstCollidable = mock(Collidable.class);
		Collidable secondCollidable = mock(Collidable.class);
		collidables.add(firstCollidable);
		collidables.add(secondCollidable);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		
		CollisionDetector detector = new CollisionDetector();
		
		when(firstCollidable.isCollidingWith(collisionPolygon)).thenReturn(false);
		when(secondCollidable.isCollidingWith(collisionPolygon)).thenReturn(true);
		
		assertSame(secondCollidable, detector.getCollidable(collidables, collisionPolygon));
		
		verify(firstCollidable).isCollidingWith(collisionPolygon);
		verify(secondCollidable).isCollidingWith(collisionPolygon);
	}
	
}
