package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.buildListeners.ModeListener;

public class Run extends JPanel {

	private static final long serialVersionUID = 1L;

	Display d;
	private ModeListener mL;

	public Run(Display display) {
		d = display;
		addButtons();
	}

	private void addButtons() {
		setLayout(new GridLayout(4, 1));

		JButton startButton = new JButton("Start");
		startButton.setBackground(Color.LIGHT_GRAY);
		startButton.setForeground(Color.white);
		startButton.setMaximumSize(new Dimension(100, 100));
		add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.setBackground(Color.LIGHT_GRAY);
		stopButton.setForeground(Color.white);
		stopButton.setMaximumSize(new Dimension(100, 100));
		add(stopButton);

		JButton pauseButton = new JButton("Pause");
		pauseButton.setBackground(Color.LIGHT_GRAY);
		pauseButton.setForeground(Color.white);
		pauseButton.setMaximumSize(new Dimension(100, 100));
		add(pauseButton);

		JButton BuildButton = new JButton("Build Mode");
		BuildButton.setBackground(Color.LIGHT_GRAY);
		BuildButton.setForeground(Color.white);
		BuildButton.setMaximumSize(new Dimension(100, 100));
		mL = new ModeListener(d,"build");
		BuildButton.addActionListener(mL);
		add(BuildButton);

	}

}
