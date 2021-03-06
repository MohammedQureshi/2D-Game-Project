package com.mohammed.game.entity.mob;

import com.mohammed.game.Game;
import com.mohammed.game.graphics.AnimatedSprite;
import com.mohammed.game.graphics.Screen;
import com.mohammed.game.graphics.Sprite;
import com.mohammed.game.graphics.SpriteSheet;
import com.mohammed.game.input.Keyboard;
import com.mohammed.game.input.Mouse;
import com.mohammed.game.projectile.PlayerProjectile;
import com.mohammed.game.projectile.Projectile;

public class Player extends Mob{
   
   private Keyboard input;
   private Sprite sprite;
   @SuppressWarnings("unused")
   private int anim = 0;
   private boolean walking = false;
   private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
   private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
   private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
   private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
   
   private AnimatedSprite animSprite = down;
   
   private int fireRate = 0;
   
   public Player(Keyboard input){
      this.input = input;
      sprite = Sprite.player_forward;
   }
   
   
   public Player(int x, int y, Keyboard input){
      this.x = x;
      this.y = y;
      this.input = input;
      sprite = Sprite.player_forward;
      fireRate = PlayerProjectile.FIRE_RATE;
      
   }
   
   public void update(){
	  if(walking) animSprite.update();
	  else animSprite.setFrame(0);
	   if(fireRate > 0) fireRate--;
      double xa = 0, ya = 0;
      double speed = 1.0;
      if(input.up) {
    	  animSprite = up;
    	  ya-=speed;
      }else if(input.down) {
    	  animSprite = down;
    	  ya+=speed;
      }
      if(input.left) {
    	  animSprite = left;
    	  xa-=speed;
      }else if(input.right) {
    	  animSprite = right;
    	  xa+=speed;
    			  
      }
      if(xa != 0 || ya != 0){
      move (xa, ya);
      walking = true;
      } else {
         walking = false;
      }
      updateShooting();
      clear();
   }
   
   private void clear() {
	for(int i = 0; i < level.getProjectiles().size(); i++){
		Projectile p = level.getProjectiles().get(i);
		if(p.isRemoved()) level.getProjectiles().remove(i);
	}
}

private void updateShooting() {
      if(Mouse.getButton() == 1 && fireRate <= 0){
         double dx = (Mouse.getX() - (Game.getWindowWidth())/2);
         double dy = (Mouse.getY() - (Game.getWindowHeight())/2);
         double dir = Math.atan2(dy, dx);
         shoot(x, y, dir);
         fireRate = PlayerProjectile.FIRE_RATE;
      }
      
   }

   public void render(Screen screen){
      int flip = 0;
      sprite = animSprite.getSprite();
      screen.renderMob((int)(x - 16), (int)(y - 16), sprite, flip);
   }
   
}