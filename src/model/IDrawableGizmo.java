package model;

import java.util.List;

import physics.Circle;
import physics.LineSegment;

/*
 * This interface is used between the model and view
 * in order to ensure that the view does not have
 * more information about the gizmos than is required
 * e.g. the view does not need to know about the gizmo's hit rate
 */
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
