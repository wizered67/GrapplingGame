package com.wizered67.game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StaticEntity implements Entity {
	private ArrayList<BoundingShape> boundingShapes;
	private Vector2 position;
	public StaticEntity(Object userData, Rectangle2D.Float rect, Vector2 position){
		boundingShapes = new ArrayList<BoundingShape>();
		rect.setRect(0, 0, rect.getWidth(), rect.getHeight());
		BoundingShape collision = new BoundingShape(this, rect);
		collision.setUserData(userData);
		boundingShapes.add(collision);
		this.position = position;
	}
	

	
	public void collide(Collision c){
		
	}
	
	@Override
	public ArrayList<BoundingShape> getBoundingShapes() {
		for (BoundingShape bs : boundingShapes){
			bs.setPosition(position);
		}
		return boundingShapes;
	}
	
	public void setPosition(Vector2 np){
		this.position = np;
	}
	
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return position.x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return position.y;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postCollisionUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	public TextureRegion getSprite(){
		return null;
	}



	@Override
	public BoundingShape getPrimaryHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

}
