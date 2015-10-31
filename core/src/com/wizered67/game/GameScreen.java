package com.wizered67.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
public class GameScreen implements Screen {
	 

    MyGame game; // Note it's "MyGame" not "Game"
    SpriteBatch batch;
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	PlayerEntity player;

    // constructor to keep a reference to the main Game class
     public GameScreen(MyGame game){
    	this.game = game;
        batch = new SpriteBatch();
     	world = new World(new Vector2(0, 0f),true);
     	world.setContactListener(new MyContactListener());
		player = new PlayerEntity("Player 1", world);
		 
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
		          getHeight());
     }
     
     @Override
     public void render(float delta) {
        // update and draw stuff
    	Gdx.gl.glClearColor(1, 1, 1, 1);
  		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	camera.update();
        // Step the physics simulation forward at a rate of 60hz
        world.step(1f/60f, 6, 2);
        
        //body.setPosition()
        
        // Set the sprite's position from the updated physics body location
        //player.setPosition((body.getPosition().x * Constants.PPM) - player.
        //                    getWidth()/2 , 
        //         (body.getPosition().y * Constants.PPM) - player.getHeight()/2 )
        //          ;
         // Ditto for rotation

         
        batch.setProjectionMatrix(camera.combined);
         
         // Scale down the sprite batches projection matrix to box2D size
        debugMatrix = batch.getProjectionMatrix().cpy().scale(Constants.PPM, 
                       Constants.PPM, 0);
         
        batch.begin();
         
        	batch.draw(player.getSprite(), player.getSprite().getX(), player.getSprite().getY(),player.getSprite().getOriginX(),
                        player.getSprite().getOriginY(),
                 player.getSprite().getWidth(),player.getSprite().getHeight(),player.getSprite().getScaleX(),player.
                                 getSprite().getScaleY(),player.getSprite().getRotation());
                         
        batch.end();
         
         // Now render the physics world using our scaled down matrix
         // Note, this is strictly optional and is, as the name suggests, just 
   
        debugRenderer.render(world, debugMatrix);
    	
 		
     }


    @Override
     public void resize(int width, int height) {
     }


    @Override
     public void show() {
          // called when this screen is set as the screen with game.setScreen();
     }


    @Override
     public void hide() {
          // called when current screen changes from this to a different screen
     }


    @Override
     public void pause() {
     }


    @Override
     public void resume() {
     }


    @Override
     public void dispose() {
             // never called automatically
     }
}