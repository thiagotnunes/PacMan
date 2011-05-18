package com.pacman.entity.movement;

import java.util.Map;

public class MovementFactory {

	private Map<Integer, Movement> movements;
	private Map<Movement, Stopped> stoppedMovements;
	private Movement defaultDirection;
	private NullMovement nullMovement;

	public MovementFactory(Movement defaultDirection,
			Map<Integer, Movement> movements,
			Map<Movement, Stopped> stoppedMovements) {
		this.nullMovement = new NullMovement();
		this.defaultDirection = defaultDirection;
		this.movements = movements;
		this.stoppedMovements = stoppedMovements;
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

	public NullMovement nullMovement() {
		return nullMovement;
	}

}
