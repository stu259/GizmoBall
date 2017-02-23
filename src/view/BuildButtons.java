package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.buildListeners.*;

public class BuildButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	private IDisplay d;
	private ChangeButtonListener cBL;
	private ModeListener mL;
	private JButton gizmosButton, operationsButton, setupButton, backButton;
	private JPanel main;
	private JPanel gizmo;
	private JPanel operation;
	private JPanel setup;

	public BuildButtons(IDisplay display) {
		setLayout(new CardLayout());
		d = display;
		addButtons();
		gizmos();
		operations();
		setup();
		add(main, "main");
		add(gizmo, "gizmo");
		add(operation, "operation");
		add(setup, "setup");
	}

	public void changeButtons(String b) {
		CardLayout cardLayout = (CardLayout) getLayout();
		cardLayout.show(this, b);
	}

	public void addButtons() {
		main = new JPanel(new GridLayout(4, 1));

		gizmosButton = new JButton("Add Gizmos");
		gizmosButton.setBackground(Color.LIGHT_GRAY);
		gizmosButton.setForeground(Color.white);
		cBL = new ChangeButtonListener(d,"gizmo");
		gizmosButton.addActionListener(cBL);
		main.add(gizmosButton);

		JButton operationsButton = new JButton("Operations");
		operationsButton.setBackground(Color.LIGHT_GRAY);
		operationsButton.setForeground(Color.white);
		cBL = new ChangeButtonListener(d,"operation");
		operationsButton.addActionListener(cBL);
		main.add(operationsButton);

		JButton setupButton = new JButton("Setup");
		setupButton.setBackground(Color.LIGHT_GRAY);
		setupButton.setForeground(Color.white);
		cBL = new ChangeButtonListener(d,"setup");
		setupButton.addActionListener(cBL);
		main.add(setupButton);

		JButton RunButton = new JButton("Run Mode");
		RunButton.setBackground(Color.LIGHT_GRAY);
		RunButton.setForeground(Color.white);
		mL = new ModeListener(d,"run");
		RunButton.addActionListener(mL);
		main.add(RunButton);

	}

	public void gizmos() {
		gizmo = new JPanel(new GridLayout(6, 1));

		JButton squareButton = new JButton("Square");
		squareButton.setBackground(Color.LIGHT_GRAY);
		squareButton.setForeground(Color.white);
		gizmo.add(squareButton);

		JButton circleButton = new JButton("Circle");
		circleButton.setBackground(Color.LIGHT_GRAY);
		circleButton.setForeground(Color.white);
		gizmo.add(circleButton);

		JButton triangleButton = new JButton("Triangle");
		triangleButton.setBackground(Color.LIGHT_GRAY);
		triangleButton.setForeground(Color.white);
		gizmo.add(triangleButton);

		JButton lFlipperButton = new JButton("Left Flipper");
		lFlipperButton.setBackground(Color.LIGHT_GRAY);
		lFlipperButton.setForeground(Color.white);
		gizmo.add(lFlipperButton);

		JButton rFlipperButton = new JButton("Right Flipper");
		rFlipperButton.setBackground(Color.LIGHT_GRAY);
		rFlipperButton.setForeground(Color.white);
		gizmo.add(rFlipperButton);

		JButton backButton = new JButton("Back");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setForeground(Color.white);
		gizmo.add(backButton);
		cBL = new ChangeButtonListener(d,"main");;
		backButton.addActionListener(cBL);
	}

	public void operations() {
		operation = new JPanel(new GridLayout(8, 1));

		JButton rotateButton = new JButton("Rotate");
		rotateButton.setBackground(Color.LIGHT_GRAY);
		rotateButton.setForeground(Color.white);
		operation.add(rotateButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.LIGHT_GRAY);
		deleteButton.setForeground(Color.white);
		operation.add(deleteButton);

		JButton moveButton = new JButton("Move");
		moveButton.setBackground(Color.LIGHT_GRAY);
		moveButton.setForeground(Color.white);
		operation.add(moveButton);

		JButton clearButton = new JButton("Clear");
		clearButton.setBackground(Color.LIGHT_GRAY);
		clearButton.setForeground(Color.white);
		operation.add(clearButton);

		JButton connectButton = new JButton("Connect");
		connectButton.setBackground(Color.LIGHT_GRAY);
		connectButton.setForeground(Color.white);
		operation.add(connectButton);

		JButton bindButton = new JButton("Bind Key");
		bindButton.setBackground(Color.LIGHT_GRAY);
		bindButton.setForeground(Color.white);
		operation.add(bindButton);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.setBackground(Color.LIGHT_GRAY);
		disconnectButton.setForeground(Color.white);
		operation.add(disconnectButton);

		JButton backButton = new JButton("Back");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setForeground(Color.white);
		cBL = new ChangeButtonListener(d,"main");;
		backButton.addActionListener(cBL);
		operation.add(backButton);
	}

	public void setup() {
		setup = new JPanel(new GridLayout(5, 1));

		JPanel ballPanel = new JPanel();
		ballPanel.setLayout(new GridLayout(1, 2));
		JPanel ballInputPanel = new JPanel();
		ballInputPanel.setLayout(new GridLayout(2, 1));
		JButton ballButton = new JButton("Place Ball");
		ballButton.setBackground(Color.LIGHT_GRAY);
		ballButton.setForeground(Color.white);
		ballPanel.add(ballButton);
		JTextField ballVInput1 = new JTextField();
		ballInputPanel.add(ballVInput1);
		JTextField ballVInput2 = new JTextField();
		ballInputPanel.add(ballVInput2);
		ballPanel.add(ballInputPanel);
		setup.add(ballPanel);

		JButton absorberButton = new JButton("Place Absorber");
		absorberButton.setBackground(Color.LIGHT_GRAY);
		absorberButton.setForeground(Color.white);
		setup.add(absorberButton);

		JPanel frictionPanel = new JPanel();
		frictionPanel.setLayout(new GridLayout(1, 2));
		JPanel frictionInputPanel = new JPanel();
		frictionInputPanel.setLayout(new GridLayout(2, 1));
		JButton frictionButton = new JButton("Friction");
		frictionButton.setBackground(Color.LIGHT_GRAY);
		frictionButton.setForeground(Color.white);
		frictionPanel.add(frictionButton);
		JTextField frictionInput1 = new JTextField();
		frictionInputPanel.add(frictionInput1);
		JTextField frictionInput2 = new JTextField();
		frictionInputPanel.add(frictionInput2);
		frictionPanel.add(frictionInputPanel);
		setup.add(frictionPanel);

		JPanel gravityPanel = new JPanel();
		gravityPanel.setLayout(new GridLayout(1, 2));
		JButton gravityButton = new JButton("Gravity");
		gravityButton.setBackground(Color.LIGHT_GRAY);
		gravityButton.setForeground(Color.white);
		gravityPanel.add(gravityButton);
		JTextField gravityInput = new JTextField();
		gravityPanel.add(gravityInput);
		setup.add(gravityPanel);

		JButton backButton = new JButton("Back");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setForeground(Color.white);
		cBL = new ChangeButtonListener(d,"main");;
		backButton.addActionListener(cBL);
		setup.add(backButton);
	}

}