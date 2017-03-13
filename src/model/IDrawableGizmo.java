package model;

public interface IDrawableGizmo {
	public int getStartX();
	public int getStartY();
	public int getEndX();
	public int getEndY();
	public int getRotation();
	public String getGizmoType();
	public boolean isTriggered();
}
