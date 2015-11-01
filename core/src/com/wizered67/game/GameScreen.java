package com.wizered67.game;

import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.World;
public class GameScreen implements Screen {
	 

    MyGame game; // Note it's "MyGame" not "Game"
    SpriteBatch batch;
	OrthographicCamera camera;
	World world;
	PlayerEntity player;
	ShapeRenderer shapes;
    // constructor to keep a reference to the main Game class
     public GameScreen(MyGame game){
    	this.game = game;
        batch = new SpriteBatch();
		player = new PlayerEntity("Player 1");
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
		          getHeight());
		camera.setToOrtho(true);
		shapes = new ShapeRenderer();
     }
     
     @Override
     public void render(float delta) {
        // update and draw stuff
    	Gdx.gl.glClearColor(1, 1, 1, 1);
  		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapes.setProjectionMatrix(camera.combined); 
        
         
        batch.begin();
         	batch.draw(player.getSprite(), player.getX(), player.getY());
        	//batch.draw(player.getSprite(), player.getSprite().getX(), player.getSprite().getY(),player.getSprite().getOriginX(),
            //            player.getSprite().getOriginY(),
            //     player.getSprite().getWidth(),player.getSprite().getHeight(),player.getSprite().getScaleX(),player.
            //                     getSprite().getScaleY(),player.getSprite().getRotation());
        	
                         
        batch.end();
        shapes.setColor(Color.CYAN);
        shapes.begin(ShapeType.Line);
        	
        	for (BoundingShape b : player.getBoundingShapes()){
        		Rectangle2D bounds = b.getShape().getBounds2D();
        		shapes.rect((float)bounds.getX() + player.getX(), (float)bounds.getY() + player.getY(), (float)bounds.getWidth(), (float)bounds.getHeight());
        	}
        shapes.end();
         // Now render the physics world using our scaled down matrix
         // Note, this is strictly optional and is, as the name suggests, just 
        player.update();
        //debugRenderer.render(world, debugMatrix);
    	
 		
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