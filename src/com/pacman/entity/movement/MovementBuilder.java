package com.pacman.entity.movement;

import static org.newdawn.slick.Input.*;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;

import com.pacman.graphics.MovementAnimationFactory;
import com.pacman.graphics.StoppedAnimationFactory;

public class MovementBuilder {

	private Map<Integer, Movement> movements;
	private Movement defaultDirection;
	private Map<Movement, Stopped> stoppedMovements;

	protected MovementBuilder(Movement defaultDirection,
			Map<Integer, Movement> movements,
			Map<Movement, Stopped> stoppedMovements) {
		this.defaultDirection = defaultDirection;
		this.movements = movements;
		this.stoppedMovements = stoppedMovements;
	}
	
	public MovementBuilder() {
	}
	
	public void buildMovements() throws SlickException {
		MovementAnimationFactory animationFactory = new MovementAnimationFactory();
		Left left = new Left(animationFactory);
		Down down = new Down(animationFactory);
		Up up = new Up(animationFactory);
		Right right = new Right(animationFactory);
		
		defaultDirection = left;
		
		movements = new HashMap<Integer, Movement>();
		movements.put(KEY_DOWN, down);
		movements.put(KEY_UP, up);
		movements.put(KEY_RIGHT, right);
		movements.put(KEY_LEFT, left);
		
		stoppedMovements = new HashMap<Movement, Stopped>();
		StoppedAnimationFactory stoppedFactory = new StoppedAnimationFactory();
		stoppedMovements.put(up, new Stopped(stoppedFactory, up));
		stoppedMovements.put(down, new Stopped(stoppedFactory, down));
		stoppedMovements.put(right, new Stopped(stoppedFactory, right));
		stoppedMovements.put(left, new Stopped(stoppedFactory, left));
	}

	public Movement from(Integer key) {
		return movements.get(key);
	}

	public Movement defaultMovement() {
		return defaultDirection;
	}

	public Stopped stoppedFrom(Movement movement) {
		return stoppedMovements.get(movement);
	}

}
