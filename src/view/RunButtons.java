package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MagicKeyListener;
import controller.TimerListener;
import controller.runListeners.RunListener;

public class RunButtons extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map<String, ActionListener> listeners;
	private RunListener runListener;
	private TimerListener timer;

	public RunButtons(Map<String, ActionListener> l, RunListener rL, TimerListener t) {
		runListener = rL;
		listeners = l;
		timer = t;
		addButtons();
	}

	private void addButtons() {
		setLayout(new GridLayout(4, 1));

		JButton startButton = new JButton("Start");
		startButton.addActionListener(timer);
		startButton.addKeyListener(new MagicKeyListener(runListener));
		buttonSetup(startButton);
		add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(timer);
		buttonSetup(stopButton);
		add(stopButton);

		JButton tickButton = new JButton("Tick");
		tickButton.addActionListener(listeners.get("tL"));
		buttonSetup(tickButton);
		add(tickButton);

		JButton buildButton = new JButton("Build Mode");
		buildButton.setActionCommand("build");
		buildButton.addActionListener(listeners.get("modeL"));
		buttonSetup(buildButton);
		add(buildButton);

	}

	private void buttonSetup(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);
		button.setPreferredSize(new Dimension(100, 40));
	}

}
