package model;
import java.util.HashSet;
import java.util.Set;

public class Model implements IModel{
/**
    * <pre>
    *           0..*     0..*
    * Model ------------------------> IGizmo
    *           model        &gt;       iGizmo
    * </pre>
    */
   private Set<IGizmo> iGizmo;
   
   public Set<IGizmo> getIGizmo() {
      if (this.iGizmo == null) {
         this.iGizmo = new HashSet<IGizmo>();
      }
      return this.iGizmo;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Model ------------------------> Ball
    *           model        &gt;       ball
    * </pre>
    */
   private Set<Ball> ball;
   
   public Set<Ball> getBall() {
      if (this.ball == null) {
         this.ball = new HashSet<Ball>();
      }
      return this.ball;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Model ------------------------> Absorber
    *           model        &gt;       absorber
    * </pre>
    */
   private Set<Absorber> absorber;
   
   public Set<Absorber> getAbsorber() {
      if (this.absorber == null) {
         this.absorber = new HashSet<Absorber>();
      }
      return this.absorber;
   }
   

	@Override
	public void addGizmo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBall() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAbsorber() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateGizmo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectGizmo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectGizmo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyConnectGizmo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGizmo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFriction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGravity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveGizmo() {
		// TODO Auto-generated method stub
		
	}

}
