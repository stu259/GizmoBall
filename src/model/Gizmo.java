package model;

import java.util.ArrayList;
import java.util.List;

import physics.*;

public class Gizmo implements IGizmo{

	protected int x, y, angle, size, currentAngle, hit;
	protected double coef, angularVel;
	protected String key, keyboardPress;
	protected List<IGizmo> outgoingConnections;//gizmo to trigger when this gizmo is hit by the ball
	protected List<IGizmo> incomingConnections;//list of gizmos which can trigger this gizmo
	protected boolean triggered, rotating;
	protected List<LineSegment> lines;
	protected List<Circle> corners;

	public Gizmo(int x, int y){
		this.x = x;
		this.y= y;
		angle = 0;  //default angle facing upward
		outgoingConnections = new ArrayList<IGizmo>();
		incomingConnections = new ArrayList<IGizmo>();
		key = null;
		keyboardPress = null;
		triggered = false;
		lines = new ArrayList<LineSegment>();
		corners = new ArrayList<Circle>();
		rotating = false;
		angularVel = 0;
		currentAngle = 0;
		hit = 0;
	}
	
	@Override
	public boolean isHit() {
		if(hit>0) return true;
		return false;
	}
	
	@Override
	public void cooldownHit(){
		if(hit!=0)
			hit--;
	}
	
	@Override
	public void setHit(int hitNo){
		this.hit = hitNo;
	}

	@Override
	public Vect getPivotPoint() {
		return this.getCenter();
	}

	@Override
	public double getAngularVel() {
		return angularVel;
	}

	@Override
	public void setAngularVel(double angularVel) {
		this.angularVel = angularVel;
	}

	@Override
	public void rotateOnPivot(boolean state){
		rotating = state;
	}
	
	@Override
	public boolean isRotatingOnPivot(){
		return rotating;
	}
	
	@Override
	public void setCorners(List<Circle> corners) {
		this.corners.clear();
		this.corners.addAll(corners);
	}

	@Override
	public void setLines(List<LineSegment> lines) {
		this.lines.clear();
		this.lines.addAll(lines);
	}


	@Override	
	public List<LineSegment> getLines() {
		return lines;
	}
	
	@Override
	public List<Circle> getCorners() {
		return corners;
	}

	@Override
	public void trigger(){
		triggered = !triggered;
	}
	
	@Override
	public boolean triggered(){
		return triggered;
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
		this.redraw();
	}
	
	@Override
	public Vect getCenter(){
		double x1 = (double) this.getStartX();
		double y1 = (double) this.getStartY();
		double x2 = (double) this.getEndX();
		double y2 = (double) this.getEndY();
		
		
		double centerX = (x2+x1)/2;
		double centerY = (y2+y1)/2;
		return new Vect(centerX, centerY);
	}
	
	@Override
	public void rotate() {
		if(angle == 270) angle = 0;
		else angle += 90;

		List<LineSegment> newLines = new ArrayList<LineSegment>();
		List<Circle> newCorners = new ArrayList<Circle>();
		
		for(LineSegment line : lines){
			newLines.add(Geometry.rotateAround(line, this.getCenter(), Angle.DEG_90));
			
		}
		for(Circle corner : corners){
			newCorners.add(Geometry.rotateAround(corner, getCenter(), Angle.DEG_90));
		}
		
		lines.clear();
		corners.clear();
		
		lines.addAll(newLines);
		corners.addAll(newCorners);
	}

	@Override
	public double getCoef() {
		return coef;
	}

	@Override
	public int getRotation() {
		return angle;
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
	public List<IGizmo> getOutgoingConnections() {
		return outgoingConnections;
	}
	
	@Override
	public void setOutgoingConnection(IGizmo connectedGizmo) {
		this.outgoingConnections.add(connectedGizmo);
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
	public void removeOutgoingConnection(IGizmo gizmo){
		outgoingConnections.remove(gizmo);
	}

	@Override
	public void clearOutgoingConnections(){
		outgoingConnections.clear();
	}
	
	@Override
	public String gizmoType() {
		return "gizmo";
	}
	
	@Override
	public double getCurrentAngle(){
		return currentAngle;
	}
	
	protected void redraw(){
		return;
	}

}
