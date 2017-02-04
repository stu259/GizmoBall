package model;
public interface IModel {
	public boolean addGizmo(IGizmo gizmo, String key);
	public void addBall(Ball ball);
	public void addAbsorber();
	public void rotateGizmo(IGizmo gizmo);
	public void connectGizmo(IGizmo gizmo1, IGizmo gizmo2);
	public boolean disconnectGizmo(IGizmo gizmo);
	public void keyConnectGizmo(IGizmo gizmo, String key);
	public void removeKey(IGizmo gizmo);
    public void deleteGizmo(IGizmo gizmo);
    public void clear();
    public void setFriction(double f);
    public void setGravity(double g);
    public void moveGizmo(int x, int y, IGizmo gizmo);
	public void runMode();
	void moveBalls();
	void resume();
	void pause();
}