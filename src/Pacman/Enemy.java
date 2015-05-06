package Pacman;

import java.util.Random;

public class Enemy extends Entity {

	Enemy(int x, int y){
		this.posX = x;
		this.posY = y;
		this.rand = new Random();
	}
	
	public int GetTargetposX(){
		return this.TargetposX;
	}
	public int GetTargetposY(){
		return this.TargetposY;
	}
	
	public void SetPosX(float x){
		this.posX = x;
	}
	public void SetPosY(float y){
		this.posY = y;
	}
	
	public float lerp(float start, float end, float speed){
		if(speed<0){
			return start;
		}
		return start+speed*(end-start);
	}
	
	public void moveOneStep(int x, int y){
		this.posX = x;
		this.posY = y;
	}	
}
