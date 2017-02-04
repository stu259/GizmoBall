package model;
public interface IModel {
	public boolean addGizmo(IGizmo gizmo);
	public void addBall(Ball ball);
	public void addAbsorber();
	public void rotateGizmo();
	public void connectGizmo();
	public void disconnectGizmo();
	public void keyConnectGizmo();
    public void deleteGizmo();
    public void clear();
    public void setFriction();
    public void setGravity();
    public void moveGizmo();
	public void runMode();
}