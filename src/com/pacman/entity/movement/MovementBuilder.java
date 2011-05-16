package com.pacman.entity.movement;

import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;

import com.pacman.graphics.AnimationFactory;

public class MovementBuilder {

	private Map<Integer, Movement> movements;
	private Movement defaultDirection;
	private AnimationFactory animationFactory;

	protected MovementBuilder(Movement defaultDirection, Map<Integer, Movement> movements) {
		this.defaultDirection = defaultDirection;
		this.movements = movements;
	}
	
	public MovementBuilder() {
	}
	
	public void buildMovements() throws SlickException {
		animationFactory = new AnimationFactory();
		defaultDirection = new Left(animationFactory);
		
		movements = new HashMap<Integer, Movement>();
		movements.put(KEY_DOWN, new Down(animationFactory));
		movements.put(KEY_UP, new Up(animationFactory));
		movements.put(KEY_RIGHT, new Right(animationFactory));
		movements.put(KEY_LEFT, defaultDirection);
		
	}

	public Movement from(Integer key) {
		return movements.get(key);
	}

	public Movement defaultMovement() {
		return defaultDirection;
	}

}
