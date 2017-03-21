package model;

import java.util.List;
import java.util.Observer;

public interface IDrawableModel {
	public List<IDrawableGizmo> drawableGizmos();

	public List<IDrawableBall> drawableBalls();
	
	public void addObserver(Observer o);
	
	public void tick();

}
