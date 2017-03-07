package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ModeListener;
import controller.TimerListener;
import controller.runListeners.TickListener;
import model.IModel;

public class RunButtons extends JPanel {

	private static final long serialVersionUID = 1L;

	private ActionListener mL, tL;
	private TimerListener timer;

	public RunButtons(IDisplay d, IModel m, TimerListener t) {
		timer = t;
		mL = new ModeListener(d, timer);
		tL = new TickListener(m);
		addButtons();
		m.runMode();
	}

	private void addButtons() {
		setLayout(new GridLayout(4, 1));

		JButton startButton = new JButton("Start");
		startButton.addActionListener(timer);
		buttonSetup(startButton);
		add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(timer);
		buttonSetup(stopButton);
		add(stopButton);

		JButton tickButton = new JButton("Tick");
		tickButton.addActionListener(tL);
		buttonSetup(tickButton);
		add(tickButton);

		JButton buildButton = new JButton("Build Mode");
		buildButton.setActionCommand("build");
		buildButton.addActionListener(mL);
		buttonSetup(buildButton);
		add(buildButton);

	}

	private void buttonSetup(JButton button) {
		button.setBackground(Color.LIGHT_GRAY);
		button.setPreferredSize(new Dimension(100, 40));
	}

}
