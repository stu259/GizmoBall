package model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public interface IdrawModel {
	public List<IDrawableGizmo> drawableGizmos();

	public List<IDrawableBall> drawableBalls();
	
	public void addObserver(Observer o);

}
