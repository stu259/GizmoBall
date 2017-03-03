package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


import controller.BuildListener;
import controller.TimerListener;
import controller.buildListeners.*;
import model.IModel;

public class BuildButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel main, gizmo, operation, setup;
	private BuildListener buildListener;
	private BuildBoard bb;
	private ActionListener aGL, cBL, mL;
	IDisplay display;
	IModel model;

	public BuildButtons(IDisplay d, IModel m, BuildBoard bb) {
		display = d;
		model = m;
		buildListener = new BuildListener(m, d);
		bb.addMouseListener(buildListener);
		setLayout(new CardLayout());
		aGL = new AddGizmosListener(m, buildListener, d);
		cBL = new ChangeButtonListener(d);
		mL = new ModeListener(display);


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
		gizmosButton.setActionCommand("gizmo");
		gizmosButton.addActionListener(cBL);
		main.add(gizmosButton);

		JButton operationsButton = new JButton("Operations");
		buttonSetup(operationsButton);
		operationsButton.setActionCommand("operations");
		operationsButton.addActionListener(cBL);
		main.add(operationsButton);

		JButton setupButton = new JButton("Setup");
		buttonSetup(setupButton);
		setupButton.setActionCommand("setup");
		setupButton.addActionListener(cBL);
		main.add(setupButton);

		JButton RunButton = new JButton("Run Mode");
		buttonSetup(RunButton);
		RunButton.setActionCommand("run");
		RunButton.addActionListener(mL);
		main.add(RunButton);
		add(main, "main");
	}

	private void gizmos() {
		gizmo = new JPanel(new GridLayout(6, 1));

		JButton squareButton = new JButton("Square");
		buttonSetup(squareButton);
		squareButton.addActionListener(aGL);
		gizmo.add(squareButton);

		JButton circleButton = new JButton("Circle");
		buttonSetup(circleButton);
		circleButton.addActionListener(aGL);
		gizmo.add(circleButton);

		JButton triangleButton = new JButton("Triangle");
		buttonSetup(triangleButton);
		triangleButton.addActionListener(aGL);
		gizmo.add(triangleButton);

		JButton lFlipperButton = new JButton("Left Flipper");
		buttonSetup(lFlipperButton);
		lFlipperButton.setActionCommand("leftflipper");
		lFlipperButton.addActionListener(aGL);
		gizmo.add(lFlipperButton);

		JButton rFlipperButton = new JButton("Right Flipper");
		buttonSetup(rFlipperButton);
		rFlipperButton.setActionCommand("rightflipper");
		rFlipperButton.addActionListener(aGL);
		gizmo.add(rFlipperButton);

		JButton backButton = new JButton("Back");
		buttonSetup(backButton);
		backButton.setActionCommand("main");
		backButton.addActionListener(cBL);
		gizmo.add(backButton);

		add(gizmo, "gizmo");
	}

	private void operations() {
		operation = new JPanel(new GridLayout(8, 1));

		JButton rotateButton = new JButton("Rotate");
		buttonSetup(rotateButton);
		rotateButton.addActionListener(new RotateListener(model, buildListener, display));
		operation.add(rotateButton);

		JButton deleteButton = new JButton("Delete");
		buttonSetup(deleteButton);
		deleteButton.addActionListener(new DeleteListener(model, buildListener, display));
		operation.add(deleteButton);

		JButton moveButton = new JButton("Move");
		buttonSetup(moveButton);
		moveButton.addActionListener(new MoveListener(model, buildListener, display));
		operation.add(moveButton);

		JButton clearButton = new JButton("Clear");
		buttonSetup(clearButton);
		clearButton.addActionListener(new ClearListener(model, bb));
		operation.add(clearButton);

		JButton connectButton = new JButton("Connect");
		buttonSetup(connectButton);
		connectButton.addActionListener(new ConnectListener(model, buildListener, display));
		operation.add(connectButton);

		JButton bindButton = new JButton("Bind Key");
		buttonSetup(bindButton);
		bindButton.addActionListener(new BindListener(model, buildListener, display));
		operation.add(bindButton);

		JButton disconnectButton = new JButton("Disconnect");
		buttonSetup(disconnectButton);
		disconnectButton.addActionListener(new DisconnectListener(model, buildListener, display));
		operation.add(disconnectButton);

		JButton backButton = new JButton("Back");
		buttonSetup(backButton);
		backButton.setActionCommand("main");
		backButton.addActionListener(cBL);
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
		absorberButton.addActionListener(new AddAbsorberListener(model, buildListener, display));
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
		backButton.setActionCommand("main");
		backButton.addActionListener(cBL);
		setup.add(backButton);

		add(setup, "setup");
	}

	private void buttonSetup(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.white);
	}

}