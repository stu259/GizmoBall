package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MagicKeyListener;
import controller.ModeListener;
import controller.buildListeners.*;
import controller.runListeners.RunListener;
import model.IModel;

public class BuildButtons extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map<String, ActionListener> listeners;
	private BuildListener buildListener;

	public BuildButtons(Map<String, ActionListener> l, BuildListener bL, BuildBoard bb) {
		buildListener = bL;
		listeners = l;	
		bb.addMouseListener(buildListener); 		
		bb.addKeyListener(new MagicKeyListener(buildListener));		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gizmos();
		operations();
		setup();
	}

	private void gizmos() {
		JPanel gizmo = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 5, 10, 5);
		c.weightx = 0.5;

		JButton squareButton = new JButton("Square");
		squareButton.addActionListener(listeners.get("aGL"));
		c.gridx = 0;
		c.gridy = 0;
		buttonSetup(squareButton);
		gizmo.add(squareButton, c);

		JButton circleButton = new JButton("Circle");
		circleButton.addActionListener(listeners.get("aGL"));
		c.gridx = 1;
		c.gridy = 0;
		buttonSetup(circleButton);
		gizmo.add(circleButton, c);

		JButton triangleButton = new JButton("Triangle");
		triangleButton.addActionListener(listeners.get("aGL"));
		c.gridx = 2;
		c.gridy = 0;
		buttonSetup(triangleButton);
		gizmo.add(triangleButton, c);

		JButton lFlipperButton = new JButton("Left Flipper");
		lFlipperButton.setActionCommand("leftflipper");
		lFlipperButton.addActionListener(listeners.get("aGL"));
		c.gridx = 0;
		c.gridy = 1;
		buttonSetup(lFlipperButton);
		gizmo.add(lFlipperButton, c);

		JButton rFlipperButton = new JButton("Right Flipper");
		rFlipperButton.setActionCommand("rightflipper");
		rFlipperButton.addActionListener(listeners.get("aGL"));
		c.gridx = 1;
		c.gridy = 1;
		buttonSetup(rFlipperButton);
		gizmo.add(rFlipperButton, c);

		JButton absorberButton = new JButton("Absorber");
		absorberButton.addActionListener(listeners.get("aAL"));
		c.gridx = 2;
		c.gridy = 1;
		buttonSetup(absorberButton);
		gizmo.add(absorberButton, c);

		JButton ballButton = new JButton("Ball");
		ballButton.addActionListener(listeners.get("aBL"));
		c.gridx = 1;
		c.gridy = 2;
		buttonSetup(ballButton);
		gizmo.add(ballButton, c);

		add(gizmo);
	}

	private void operations() {
		JPanel operation = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;

		JButton rotateButton = new JButton("Rotate");
		rotateButton.addActionListener(listeners.get("rL"));
		c.gridx = 0;
		c.gridy = 0;
		buttonSetup(rotateButton);
		operation.add(rotateButton, c);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(listeners.get("dL"));
		c.gridx = 1;
		c.gridy = 0;
		buttonSetup(deleteButton);
		operation.add(deleteButton, c);

		JButton moveButton = new JButton("Move");
		moveButton.addActionListener(listeners.get("ml"));
		c.gridx = 2;
		c.gridy = 0;
		buttonSetup(moveButton);
		operation.add(moveButton, c);
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(listeners.get("cL"));
		c.gridx = 0;
		c.gridy = 1;
		buttonSetup(clearButton);
		operation.add(clearButton, c);

		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(listeners.get("connectL"));
		c.gridx = 1;
		c.gridy = 1;
		buttonSetup(connectButton);
		operation.add(connectButton, c);

		JButton bindButton = new JButton("Bind Key");
		bindButton.addActionListener(listeners.get("bindL"));
		bindButton.addKeyListener(buildListener);
		c.gridx = 2;
		c.gridy = 1;
		buttonSetup(bindButton);
		operation.add(bindButton, c);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(listeners.get("disconnectL"));
		c.gridx = 1;
		c.gridy = 2;
		buttonSetup(disconnectButton);
		operation.add(disconnectButton, c);

		add(operation);
	}

	private void setup() {

		JPanel setup = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 5, 20);
		c.weightx = 0.5;

		JButton frictionButton = new JButton("Friction");
		frictionButton.addActionListener(listeners.get("fL"));
		setup.add(frictionButton);
		buttonSetup(frictionButton);
		setup.add(frictionButton, c);

		JButton gravityButton = new JButton("Gravity");
		gravityButton.addActionListener(listeners.get("gL"));
		setup.add(gravityButton);
		buttonSetup(gravityButton);
		setup.add(gravityButton, c);

		add(setup);

		JPanel run = new JPanel();
		JButton runButton = new JButton("Run Mode");
		runButton.setActionCommand("run");
		runButton.addActionListener(listeners.get("modeL"));
		buttonSetup(runButton);
		run.add(runButton);
		add(run);
	}

	private void buttonSetup(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(null);
		button.setPreferredSize(new Dimension(100, 40));
	}

}