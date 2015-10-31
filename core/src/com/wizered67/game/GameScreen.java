package com.wizered67.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	 

    MyGame game; // Note it's "MyGame" not "Game"
    SpriteBatch batch;
	Texture img;

    // constructor to keep a reference to the main Game class
     public GameScreen(MyGame game){
             this.game = game;
     }
     
     @Override
     public void render(float delta) {
        // update and draw stuff
    	Gdx.gl.glClearColor(1, 0, 0, 1);
 		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 		batch.begin();
 		batch.draw(img, 0, 0);
 		batch.end();
     }


    @Override
     public void resize(int width, int height) {
     }


    @Override
     public void show() {
          // called when this screen is set as the screen with game.setScreen();
    	batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
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