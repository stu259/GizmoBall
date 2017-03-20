package controller.buildListeners;

import model.IModel;
import view.IDisplay;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class RotateListener implements ActionListener, MouseInputListener {

    private IModel model;
    private BuildListener buildListener;
    private IDisplay display;

    public RotateListener(IModel m, BuildListener bL, IDisplay d) {
        model = m;
        buildListener = bL;
        display = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buildListener.setMouseListener(this);
        display.changeText("Select Gizmo to Rotate");
    }

    public void mousePressed(MouseEvent e) {
        if (!model.rotateGizmo(e.getX() / display.getScale(), e.getY() / display.getScale())) {
            display.errorPopup("Cannot perform rotate action. Keep in mind absorbers cannot be rotated.");
        }
    }


    public void mouseExited(MouseEvent e) {
        display.changeText("Select Gizmo");
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

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
