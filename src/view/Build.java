package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Build extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Display d;

	public Build() {
//		addButtons();
//		gizmo();
//		operations();
		setup();
	}

	private void addButtons() {
		setLayout(new GridLayout(4, 1));

		JButton gizmosButton = new JButton("Add Gizmos");
		gizmosButton.setMaximumSize(new Dimension(100, 100));
		add(gizmosButton);

		JButton operationsButton = new JButton("Operations");
		operationsButton.setMaximumSize(new Dimension(100, 100));
		add(operationsButton);

		JButton setupButton = new JButton("Setup");
		setupButton.setMaximumSize(new Dimension(100, 100));
		add(setupButton);

		JButton RunButton = new JButton("Run Mode");
		RunButton.setMaximumSize(new Dimension(100, 100));
		add(RunButton);
	}

	private void gizmos() {
		setLayout(new GridLayout(6, 1));

		JButton squareButton = new JButton("Square");
		squareButton.setMaximumSize(new Dimension(100, 100));
		add(squareButton);

		JButton circleButton = new JButton("Circle");
		circleButton.setMaximumSize(new Dimension(100, 100));
		add(circleButton);

		JButton triangleButton = new JButton("Triangle");
		triangleButton.setMaximumSize(new Dimension(100, 100));
		add(triangleButton);

		JButton lFlipperButton = new JButton("Left Flipper");
		lFlipperButton.setMaximumSize(new Dimension(100, 100));
		add(lFlipperButton);

		JButton rFlipperButton = new JButton("Right Flipper");
		rFlipperButton.setMaximumSize(new Dimension(100, 100));
		add(rFlipperButton);

		JButton backButton = new JButton("Back");
		backButton.setMaximumSize(new Dimension(100, 100));
		add(backButton);
	}

	private void operations() {
		setLayout(new GridLayout(8, 1));

		JButton rotateButton = new JButton("Rotate");
		rotateButton.setMaximumSize(new Dimension(100, 100));
		add(rotateButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setMaximumSize(new Dimension(100, 100));
		add(deleteButton);

		JButton moveButton = new JButton("Move");
		moveButton.setMaximumSize(new Dimension(100, 100));
		add(moveButton);

		JButton clearButton = new JButton("Clear");
		clearButton.setMaximumSize(new Dimension(100, 100));
		add(clearButton);

		JButton connectButton = new JButton("Connect");
		connectButton.setMaximumSize(new Dimension(100, 100));
		add(connectButton);

		JButton bindButton = new JButton("Bind Key");
		bindButton.setMaximumSize(new Dimension(100, 100));
		add(bindButton);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.setMaximumSize(new Dimension(100, 100));
		add(disconnectButton);

		JButton backButton = new JButton("Back");
		backButton.setMaximumSize(new Dimension(100, 100));
		add(backButton);
	}

	private void setup() {
		setLayout(new GridLayout(5, 1));

		JPanel ballPanel = new JPanel();
		ballPanel.setLayout(new GridLayout(1, 2));
		JPanel ballInputPanel = new JPanel();
		ballInputPanel.setLayout(new GridLayout(2, 1));
		JButton ballButton = new JButton("Place Ball");
		ballButton.setMaximumSize(new Dimension(100, 100));
		ballPanel.add(ballButton);
		JTextField ballVInput1 = new JTextField();
		ballInputPanel.add(ballVInput1);
		JTextField ballVInput2 = new JTextField();
		ballInputPanel.add(ballVInput2);
		ballPanel.add(ballInputPanel);
		add(ballPanel);

		JButton absorberButton = new JButton("Place Absorber");
		absorberButton.setMaximumSize(new Dimension(100, 100));
		add(absorberButton);

		JPanel frictionPanel = new JPanel();
		frictionPanel.setLayout(new GridLayout(1, 2));
		JPanel frictionInputPanel = new JPanel();
		frictionInputPanel.setLayout(new GridLayout(2, 1));
		JButton frictionButton = new JButton("Friction");
		frictionButton.setMaximumSize(new Dimension(100, 100));
		frictionPanel.add(frictionButton);
		JTextField frictionInput1 = new JTextField();
		frictionInputPanel.add(frictionInput1);
		JTextField frictionInput2 = new JTextField();
		frictionInputPanel.add(frictionInput2);
		frictionPanel.add(frictionInputPanel);
		add(frictionPanel);

		JPanel gravityPanel = new JPanel();
		gravityPanel.setLayout(new GridLayout(1, 2));
		JButton gravityButton = new JButton("Gravity");
		gravityButton.setMaximumSize(new Dimension(100, 100));
		gravityPanel.add(gravityButton);
		JTextField gravityInput = new JTextField();
		gravityPanel.add(gravityInput);
		add(gravityPanel);

		JButton backButton = new JButton("Back");
		backButton.setMaximumSize(new Dimension(100, 100));
		add(backButton);
	}

}
