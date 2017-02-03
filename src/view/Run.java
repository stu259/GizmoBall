package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Run extends JPanel {

	private static final long serialVersionUID = 1L;

	Display display;

	public Run() {
		addButtons();
	}

	private void addButtons() {
		setLayout(new GridLayout(4, 1));

		JButton startButton = new JButton("Start");
		startButton.setMaximumSize(new Dimension(100, 100));
		add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.setMaximumSize(new Dimension(100, 100));
		add(stopButton);

		JButton pauseButton = new JButton("Pause");
		pauseButton.setMaximumSize(new Dimension(100, 100));
		add(pauseButton);

		JButton BuildButton = new JButton("Build Mode");
		BuildButton.setMaximumSize(new Dimension(100, 100));
		add(BuildButton);

	}

}
