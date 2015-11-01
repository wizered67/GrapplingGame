package com.wizered67.game;

import java.awt.Shape;
import java.awt.geom.Area;


public class BoundingShape {
	private Object userData;
	private Shape shape;
	private PlayerEntity owner;
	private boolean sensor = false;
	public BoundingShape(PlayerEntity creator, Shape s){
		owner = creator;
		shape = s;
	}
	
	public Object getUserData(){
		return userData;
	}
	
	public void setUserData(Object data){
		userData = data;
	}
	
	public Shape getShape(){
		return shape;
	}
	
	public PlayerEntity getOwner(){
		return owner;
	}
	
	public boolean intersects(BoundingShape other){
		Area areaA = new Area(shape);
		areaA.intersect(new Area(other.getShape()));
		return !areaA.isEmpty();
	}

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}
	
}
