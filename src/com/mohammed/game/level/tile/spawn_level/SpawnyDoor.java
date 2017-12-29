package com.mohammed.game.level.tile.spawn_level;

import com.mohammed.game.graphics.Screen;
import com.mohammed.game.graphics.Sprite;
import com.mohammed.game.level.tile.Tile;

public class SpawnyDoor extends Tile {

	public SpawnyDoor(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return false;
	}

}
