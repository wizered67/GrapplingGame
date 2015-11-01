package com.wizered67.game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerEntity {
	private String id;
	private TextureRegion sprite;
	private ArrayList<BoundingShape> boundingShapes;
	private int numberGroundContacts = 0;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	public PlayerEntity(String id){
		this.id = id;
		boundingShapes = new ArrayList<BoundingShape>();
		Texture tex = new Texture("batman.png");
		sprite = new TextureRegion(tex);
		sprite.flip(false, true);
        
		position = new Vector2(Gdx.graphics.getWidth() / 2 - getWidth()/2,Gdx.graphics.getHeight() / 2 -getHeight()/2);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
     	
     	
     	BoundingShape mainBody = new BoundingShape(this, new Rectangle2D.Float(0, 0, getWidth(), getHeight()));
     	mainBody.setUserData("player_body");
     	BoundingShape foot = new BoundingShape(this, new Rectangle2D.Float(0, getHeight() - 4, getWidth(), 4));
     	foot.setSensor(true);
     	foot.setUserData("player_foot");
     	boundingShapes.add(mainBody);
     	boundingShapes.add(foot);
     	
	}
	
	public void update(){
		updatePhysics();
	}
	
	public void updatePhysics(){
		boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
		velocity.add(new Vector2(
				(right) ? 1 : 0 + ((left) ? -1 : 0), (up) ? -1 : 0 + ((down) ? 1 : 0)
		));
		velocity.add(acceleration);
		position.add(velocity);
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Vector2 getVelocity(){
		return velocity;
	}
	
	public Vector2 getAcceleration(){
		return acceleration;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public float getWidth(){
		return sprite.getRegionWidth();
	}
	
	public float getHeight(){
		return sprite.getRegionHeight();
	}
	
	public boolean onGround(){
		return (numberGroundContacts > 0);
	}
	
	public void addGroundContacts(int amount){
		numberGroundContacts += amount;
		if (numberGroundContacts < 0)
			numberGroundContacts = 0;
	}
	
	public ArrayList<BoundingShape> getBoundingShapes(){
		return boundingShapes;
	}
	
	public TextureRegion getSprite(){
		return sprite;
	}
	
	public String getId(){
		return id;
	}
}
