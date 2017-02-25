package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JPanel;


import controller.BuildListener;
import controller.buildListeners.*;
import model.IModel;

public class BuildButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	private IDisplay display;
	private JPanel main, gizmo, operation, setup;
	private IModel model;
	private BuildListener buildListener;
	private BuildBoard buildBoard;

	public BuildButtons(IDisplay d, IModel m, BuildBoard bb) {

		buildListener = new BuildListener(m, display);
		buildBoard = bb;
		buildBoard.addMouseListener(buildListener);
		setLayout(new CardLayout());
		display = d;
		model = m;
		addButtons();
		gizmos();
		operations();
		setup();
	}

	public void changeButtons(String b) {
		CardLayout cardLayout = (CardLayout) getLayout();
		cardLayout.show(this, b);
	}

	private void addButtons() {
		main = new JPanel(new GridLayout(4, 1));

		JButton gizmosButton = new JButton("Add Gizmos");
		buttonSetup(gizmosButton);
		gizmosButton.addActionListener(new ChangeButtonListener(display, "gizmo"));
		main.add(gizmosButton);

		JButton operationsButton = new JButton("Operations");
		buttonSetup(operationsButton);
		operationsButton.addActionListener(new ChangeButtonListener(display, "operations"));
		main.add(operationsButton);

		JButton setupButton = new JButton("Setup");
		buttonSetup(setupButton);
		setupButton.addActionListener(new ChangeButtonListener(display, "setup"));
		main.add(setupButton);

		JButton RunButton = new JButton("Run Mode");
		buttonSetup(RunButton);
		RunButton.addActionListener(new ModeListener(display, "run"));
		main.add(RunButton);
		add(main, "main");
	}

	private void gizmos() {
		gizmo = new JPanel(new GridLayout(6, 1));

		JButton squareButton = new JButton("Square");
		buttonSetup(squareButton);
		squareButton.addActionListener(new AddGizmosListener(model, "square", buildListener));
		gizmo.add(squareButton);

		JButton circleButton = new JButton("Circle");
		buttonSetup(circleButton);
		circleButton.addActionListener(new AddGizmosListener(model, "circle", buildListener));
		gizmo.add(circleButton);

		JButton triangleButton = new JButton("Triangle");
		buttonSetup(triangleButton);
		triangleButton.addActionListener(new AddGizmosListener(model, "triangle", buildListener));
		gizmo.add(triangleButton);

		JButton lFlipperButton = new JButton("Left Flipper");
		buttonSetup(lFlipperButton);
		lFlipperButton.addActionListener(new AddGizmosListener(model, "lFlipper", buildListener));
		gizmo.add(lFlipperButton);

		JButton rFlipperButton = new JButton("Right Flipper");
		buttonSetup(rFlipperButton);
		rFlipperButton.addActionListener(new AddGizmosListener(model, "rFlipper", buildListener));
		gizmo.add(rFlipperButton);

		JButton backButton = new JButton("Back");
		buttonSetup(backButton);
		backButton.addActionListener(new ChangeButtonListener(display, "main"));
		gizmo.add(backButton);

		add(gizmo, "gizmo");
	}

	private void operations() {
		operation = new JPanel(new GridLayout(8, 1));

		JButton rotateButton = new JButton("Rotate");
		buttonSetup(rotateButton);
		rotateButton.addActionListener(new RotateListener(model, buildListener));
		operation.add(rotateButton);

		JButton deleteButton = new JButton("Delete");
		buttonSetup(deleteButton);
		deleteButton.addActionListener(new DeleteListener(model, buildListener));
		operation.add(deleteButton);

		JButton moveButton = new JButton("Move");
		buttonSetup(moveButton);
		moveButton.addActionListener(new MoveListener(model, buildListener));
		operation.add(moveButton);

		JButton clearButton = new JButton("Clear");
		buttonSetup(clearButton);
		clearButton.addActionListener(new ClearListener(model));
		operation.add(clearButton);

		JButton connectButton = new JButton("Connect");
		buttonSetup(connectButton);
		connectButton.addActionListener(new ConnectListener(model, buildListener));
		operation.add(connectButton);

		JButton bindButton = new JButton("Bind Key");
		buttonSetup(bindButton);
		bindButton.addActionListener(new BindListener(model, buildListener));
		operation.add(bindButton);

		JButton disconnectButton = new JButton("Disconnect");
		buttonSetup(disconnectButton);
		disconnectButton.addActionListener(new DisconnectListener(model, buildListener));
		operation.add(disconnectButton);

		JButton backButton = new JButton("Back");
		buttonSetup(backButton);
		backButton.addActionListener(new ChangeButtonListener(display, "main"));
		operation.add(backButton);

		add(operation, "operations");
	}

	private void setup() {

		setup = new JPanel(new GridLayout(5, 1));

		JButton ballButton = new JButton("Place Ball");
		buttonSetup(ballButton);
		ballButton.addActionListener(new AddBallListener(model, buildListener, display));
		setup.add(ballButton);

		JButton absorberButton = new JButton("Place Absorber");
		buttonSetup(absorberButton);
		absorberButton.addActionListener(new AddAbsorberListener(model, buildListener));
		setup.add(absorberButton);

		JButton frictionButton = new JButton("Friction");
		buttonSetup(frictionButton);
		frictionButton.addActionListener(new FrictionListener(model, display));
		setup.add(frictionButton);

		JButton gravityButton = new JButton("Gravity");
		buttonSetup(gravityButton);
		gravityButton.addActionListener(new GravityListener(model, display));
		setup.add(gravityButton);

		JButton backButton = new JButton("Back");
		buttonSetup(backButton);
		backButton.addActionListener(new ChangeButtonListener(display, "main"));
		setup.add(backButton);

		add(setup, "setup");
	}

	private void buttonSetup(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.white);
	}

}