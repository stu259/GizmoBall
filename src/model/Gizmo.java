package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Gizmo implements IGizmo{

	protected int x, y, angle, size;
	protected double coef;
	protected Color color;
	protected String key, keyboardPress;
	protected IGizmo outgoingConnection;//gizmo to trigger when this gizmo is hit by the ball
	protected List<IGizmo> incomingConnections;//list of gizmos which can trigger this gizmo

	public Gizmo(int x, int y){
		this.x = x;
		this.y= y;
		this.color = Color.BLUE;
		angle = 0;  //default angle facing upward
		outgoingConnection = null;
		incomingConnections = new ArrayList<IGizmo>();
		key = null;
		keyboardPress = null;
	}
	
	@Override
	public int getStartX() {
		return x;
	}

	@Override
	public int getStartY() {
		return y;
	}

	@Override
	public int getEndX() {
		return x+size;
	}

	@Override
	public int getEndY() {
		return y+size;
	}
	
	@Override
	public void newPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void rotate() {
		if(angle == 270) angle = 0;
		else angle += 90;
	}
	
	
	@Override
	public void changeColor(Color color) {
		this.color = color; 
	}

	@Override
	public double getCof() {
		return coef;
	}

	@Override
	public int getRotation() {
		return angle;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public Gizmo copy(){
		return this;
	}
	
	@Override
	public void setKeyboardPress(String k){
		this.keyboardPress = k;
	}
	
	@Override
	public String getKeyboardPress(){
		return keyboardPress;
	}
	
	@Override
	public void setKey(String k){
		key = k;
	}
	
	@Override
	public String getKey(){
		return key;
	}
	
	@Override
	public IGizmo getOutgoingConnection() {
		return outgoingConnection;
	}
	
	@Override
	public void setOutgoingConnection(IGizmo connectedGizmo) {
		this.outgoingConnection = connectedGizmo;
	}
	
	@Override
	public List<IGizmo> getIncomingConnections() {
		return incomingConnections;
	}

	@Override
	public void addIncomingConnection(IGizmo gizmoConnected) {
		incomingConnections.add(gizmoConnected);
	}
	
	@Override
	public void clearIncomingConnections(){
		incomingConnections.clear();
	}
	
	@Override
	public void removeIncomingConnection(IGizmo gizmo){
		incomingConnections.remove(gizmo);
	}

	@Override
	public void clearOutgoingConnection(){
		outgoingConnection = null;
	}
	
	@Override
	public String gizmoType() {
		return "gizmo";
	}

}
