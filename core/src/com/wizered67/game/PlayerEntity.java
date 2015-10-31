package com.wizered67.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerEntity {
	private String id;
	private Texture img;
	private Sprite sprite;
	private Body body;
	private int numberGroundContacts = 0;
	private Vector2 velocity;
	public PlayerEntity(String id, World world){
		this.id = id;
		img = new Texture("batman.png");
        sprite = new Sprite(img);
     	sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);
		BodyDef bodyDef = new BodyDef();		
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) / 
		                      Constants.PPM, 
		         (sprite.getY() + sprite.getHeight()/2) / Constants.PPM);
		
		body = world.createBody(bodyDef);
		body.setUserData(this);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2 / Constants.PPM, sprite.getHeight()
		                /2 / Constants.PPM
		                );
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;
		
		Fixture mainFixture = body.createFixture(fixtureDef);
		mainFixture.setUserData("player_body");
		
		shape.setAsBox(sprite.getWidth() / 2 / Constants.PPM, 5 / Constants.PPM, new Vector2(0, -sprite.getHeight() / 2 / Constants.PPM + 4 / Constants.PPM), 0);
		
		fixtureDef.isSensor = true;
		Fixture footFixture = body.createFixture(fixtureDef);
		footFixture.setUserData("player_foot");
		shape.dispose();
	}
	
	public void updatePhysics(){
		boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
		
	}
	
	public void setPosition(Vector2 position){
		body.setTransform(position.x / Constants.PPM, position.y / Constants.PPM, body.getAngle());
		sprite.setPosition(position.x, position.y);
		
	}
	
	public boolean onGround(){
		return (numberGroundContacts > 0);
	}
	
	public void addGroundContacts(int amount){
		numberGroundContacts += amount;
		if (numberGroundContacts < 0)
			numberGroundContacts = 0;
	}
	
	public Body getBody(){
		return body;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public String getId(){
		return id;
	}
}
