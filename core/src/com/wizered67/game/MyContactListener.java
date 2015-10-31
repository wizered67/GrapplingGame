package com.wizered67.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class MyContactListener implements ContactListener{
	 
	 private boolean inCollision(String userdata1, String userdata2, Contact contact){
		 Fixture fA = contact.getFixtureA();
         Fixture fB = contact.getFixtureB();
		 if (fA != null && (userdata1.equals("") || (fA.getUserData() != null && fA.getUserData().equals(userdata1)))){
			 if (fB != null && (userdata2.equals("") || (fB.getUserData() != null && fB.getUserData().equals(userdata2))))
				 return true;
         } 
		 if (fB != null && (userdata1.equals("") || (fB.getUserData() != null && fB.getUserData().equals(userdata1)))){
			 if (fA != null && (userdata2.equals("") || (fA.getUserData() != null && fA.getUserData().equals(userdata2))))
				 return true;
         } 
		 return false;
	 }
	 
	 private Fixture getFixture(String userdata, Contact contact){
		 Fixture fA = contact.getFixtureA();
         Fixture fB = contact.getFixtureB();
         if (fA != null && fA.getUserData() != null && fA.getUserData().equals(userdata))
        	 return fA;
         if (fB != null && fB.getUserData() != null && fB.getUserData().equals(userdata))
        	 return fB;
         return null;
	 }
	 
	 @Override
     public void beginContact(Contact contact) {
         Fixture fA = contact.getFixtureA();
         Fixture fB = contact.getFixtureB();
         
         if (fA.getUserData() != null && fB.getUserData() != null)
        	 Gdx.app.log("beginContact", "between " + fA.getUserData().toString() + " and " + fB.getUserData().toString());
         else
        	 return;
       
         if (inCollision("foot", "ground", contact)){
        	 Fixture playerFixture = getFixture("foot", contact);
        	 if (playerFixture != null){
        		 PlayerEntity player = (PlayerEntity) playerFixture.getBody().getUserData();
        		 player.addGroundContacts(1); //add 1 ground contact
        	 }
         }
	 }
     @Override
     public void endContact(Contact contact) {
         Fixture fA = contact.getFixtureA();
         Fixture fB = contact.getFixtureB();
         if (fA.getUserData() != null && fB.getUserData() != null)
        	 Gdx.app.log("endContact", "between " + fA.getUserData().toString() + " and " + fB.getUserData().toString());
         else
        	 return;
         
         if (inCollision("foot", "ground", contact)){
        	 Fixture playerFixture = getFixture("foot", contact);
        	 if (playerFixture != null){
        		 PlayerEntity player = (PlayerEntity) playerFixture.getBody().getUserData();
        		 player.addGroundContacts(-1); //subtract 1 ground contact
        	 }
         }
     }
     
     @Override
     public void preSolve(Contact contact, Manifold oldManifold) {
     }

     @Override
     public void postSolve(Contact contact, ContactImpulse impulse) {
     }
}
