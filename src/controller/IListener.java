package controller;

import java.awt.event.*;
import javax.swing.event.*;

public interface IListener extends WindowListener, KeyListener, MouseInputListener {
	
	public void setKeyListener(KeyListener k);

	public void setMouseListener(MouseInputListener m) ;
}
