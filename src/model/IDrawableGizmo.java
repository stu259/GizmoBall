package model;

import java.util.List;

import physics.Circle;
import physics.LineSegment;

public interface IDrawableGizmo {
	public int getStartX();
	public int getStartY();
	public int getEndX();
	public int getEndY();
	public int getRotation();
	public String getGizmoType();
	public boolean isTriggered();
	public double getAngle();
	public boolean isHit();
	List<LineSegment> getLines();
	List<Circle> getCorners();
	
}
