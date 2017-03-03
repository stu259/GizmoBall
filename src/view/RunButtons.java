package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.TimerListener;
import controller.buildListeners.ModeListener;
import controller.runListeners.StartStopListener;
import controller.runListeners.TickListener;
import model.IModel;

public class RunButtons extends JPanel {

	private static final long serialVersionUID = 1L;

	private ActionListener mL, tL;
	private TimerListener timer;
	

	public RunButtons(IDisplay d, IModel m, TimerListener t) {
		timer=t;
		mL = new ModeListener(d,timer);
		tL = new TickListener(m);
		addButtons();		
		m.runMode();
	}

	private void addButtons() {
		setLayout(new GridLayout(4, 1));

		JButton startButton = new JButton("Start");
		startButton.setBackground(Color.LIGHT_GRAY);
		startButton.setForeground(Color.white);
		startButton.addActionListener(timer);
		startButton.setMaximumSize(new Dimension(100, 100));
		add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.setBackground(Color.LIGHT_GRAY);
		stopButton.setForeground(Color.white);
		stopButton.addActionListener(timer);
		stopButton.setMaximumSize(new Dimension(100, 100));
		add(stopButton);

		JButton tickButton = new JButton("Tick");
		tickButton.setBackground(Color.LIGHT_GRAY);
		tickButton.setForeground(Color.white);
		tickButton.addActionListener(tL);
		tickButton.setMaximumSize(new Dimension(100, 100));
		add(tickButton);

		JButton BuildButton = new JButton("Build Mode");
		BuildButton.setBackground(Color.LIGHT_GRAY);
		BuildButton.setForeground(Color.white);
		BuildButton.setMaximumSize(new Dimension(100, 100));
		BuildButton.setActionCommand("build");
		BuildButton.addActionListener(mL);
		add(BuildButton);

	}

}
