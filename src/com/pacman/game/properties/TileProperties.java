package com.pacman.game.properties;

public enum TileProperties {
	CONSUMABLE {
		@Override
		public String defaultValue() {
			return "false";
		}
	}, 
	COLLIDABLE {
		@Override
		public String defaultValue() {
			return "false";
		}
		
	};

	public String property() {
		return name().toLowerCase();
	}

	public abstract String defaultValue();

}
