package controller.buildListeners;

import model.IModel;
import view.IDisplay;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MoveListener implements ActionListener, MouseInputListener {

    private IModel model;
    private BuildListener buildListener;
    private IDisplay display;
    private int x;
    private int y;
    private double xB, yB;
    private boolean isClicked;

    public MoveListener(IModel m, BuildListener bL, IDisplay d) {
        model = m;
        buildListener = bL;
        display = d;
        isClicked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buildListener.setMouseListener(this);
        display.changeText("Select Gizmo");
        isClicked = false;
    }

    public void mousePressed(MouseEvent e) {
        if (isClicked == false) {
            x = e.getX() / display.getScale();
            y = e.getY() / display.getScale();
            xB = (double) e.getX() / (double) display.getScale();
    		yB = (double) e.getY() / (double) display.getScale();
            isClicked = true;
            display.changeText("Select New Location");
        } else if (isClicked == true) {
        	
            double newX = (double) e.getX() / (double) display.getScale();
    		double newY = (double) e.getY() / (double) display.getScale();
        	Boolean ballMoved = (model.moveBall(xB, yB, newX, newY));
        	Boolean gizmoMoved = (model.moveGizmo(x, y, e.getX() / display.getScale(), e.getY() / display.getScale()));
        	
        	if(ballMoved && !gizmoMoved){
        		
        	}
        	else if(!ballMoved && gizmoMoved){
        		
        	}
        	else if(!ballMoved && !gizmoMoved){
        		display.errorPopup("Move action invalid. First click a ball or gizmo. Second click on a empty space.");
        	}
        	
//            
//                display.errorPopup("Cannot perform move action from selected coordinates. \n"
//                        + "The first selection must be a gizmo or a ball. The second click should be empty grid position. \n"
//                        + "Keep in mind that flippers take up 4 grid positions.");
//            
            isClicked = false;
            display.changeText("Select Gizmo");
        }

    }

    public void mouseReleased(MouseEvent e) {
        if (isClicked == false) {
            display.changeText("Select Gizmo");
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
}
