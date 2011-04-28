package com.pacman.game.properties;


public enum LayerProperties {

	VISIBLE {
		@Override
		public String defaultValue() {
			return "true";
		}
	};
	
	public String property() {
		return name().toLowerCase();
	}
	
	public abstract String defaultValue();
	
}
