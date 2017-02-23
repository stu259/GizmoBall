package model;

public class DrawableGizmo implements IDrawableGizmo {

	private IGizmo gizmo;
	
	public DrawableGizmo(IGizmo gizmo){
		this.gizmo = gizmo;
	}
	
	@Override
	public int getStartX() {
		return gizmo.getStartX();
	}

	@Override
	public int getStartY() {
		return gizmo.getStartY();
	}

	@Override
	public int getEndX() {
		return gizmo.getEndX();
	}

	@Override
	public int getEndY() {
		return gizmo.getEndY();
	}
	
	public String getGizmoType() {
		return gizmo.gizmoType();
	}

	@Override
	public int getRotation() {
		return gizmo.getRotation();
	}
}
