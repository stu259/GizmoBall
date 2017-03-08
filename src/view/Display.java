package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ExitActionListener;
import controller.LoadListener;
import controller.NewBoardActionListener;
import controller.SaveListener;
import controller.TimerListener;
import model.IModel;
import model.Model;

public class Display implements IDisplay {

	private Model m;
	private JFrame frame;
	private JPanel buttons;
	private BuildButtons build;
	private RunButtons run;
	private JPanel boards;
	private BuildBoard bB;
	private RunBoard rB;
	private TimerListener timer;
	private JTextField output;

	Container cp;

	public Display(Model model) {
		m = model;
		timer= new TimerListener(model);
		initialise();
		addMenuBar();
		tidy();
	}

	private void initialise() {
		frame = new JFrame("Gizmo Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = frame.getContentPane();
		buttons = new JPanel(new CardLayout());
		boards = new JPanel(new CardLayout());
		build();
		run();
		cp.add(buttons, BorderLayout.LINE_START);
		cp.add(boards, BorderLayout.CENTER);
		output = new JTextField("Select Button");
		cp.add(output, BorderLayout.PAGE_END);
		changeMode("build");
	}

	private void addMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		JMenuItem newBoard = new JMenuItem("New");
		newBoard.addActionListener(new NewBoardActionListener(this,m));
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(new LoadListener(this, m));
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new SaveListener(this, m));
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitActionListener());

		menu.add(newBoard);
		menu.add(load);
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);

		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
	}

	private void tidy() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();

	}

	public void changeMode(String m) {
		CardLayout cardLayout = (CardLayout) buttons.getLayout();
		cardLayout.show(buttons, m);
		cardLayout = (CardLayout) boards.getLayout();
		cardLayout.show(boards, m);
		if (m == "build") {
			frame.setTitle("BUILD MODE");
		} else {
			frame.setTitle("RUN MODE");
		}
	}
	
	public void changeText(String m){
		output.setText(m);
	}

	public void build() {
		bB = new BuildBoard(700, 700, m);
		build = new BuildButtons(this, m, bB, frame);
		buttons.add(build, "build");
		boards.add(bB, "build");
	}

	public void run() {
		run = new RunButtons(this, m, timer);
		rB = new RunBoard(700, 700, m);
		buttons.add(run, "run");
		boards.add(rB, "run");
	}

	public File saveDialog() {
		String filePath = (String) JOptionPane.showInputDialog(new JFrame(), "Enter name for save", "Save Dialog",
				JOptionPane.PLAIN_MESSAGE);
		if (filePath!=null) {
			return new File(filePath + ".txt");
		} else {
			errorPopup("Invalid File Name");
			return null;
		}

	}

	public File loadDialog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt");
		fileChooser.setApproveButtonText("Load");
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int folio = fileChooser.showOpenDialog(new JFrame());
		if (folio == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
		}

	}

	public void errorPopup(String errorMessage) {
		JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public double[] inputPopup(String[] message) {
		JTextField[] textField = new JTextField[message.length];
		JPanel inputPopup = new JPanel(new BorderLayout());
		double[] output = new double[message.length];

		JPanel labels = new JPanel(new GridLayout(message.length, 1));
		for (int i = 0; i < message.length; i++) {
			labels.add(new JLabel(message[i]));
		}
		inputPopup.add(labels, BorderLayout.WEST);

		JPanel input = new JPanel(new GridLayout(message.length, 1));
		for (int i = 0; i < message.length; i++) {
			textField[i] = new JTextField();
			input.add(textField[i]);
		}
		inputPopup.add(input, BorderLayout.CENTER);

		JOptionPane.showMessageDialog(frame, inputPopup, "Input", JOptionPane.PLAIN_MESSAGE);

		try {
			for (int i = 0; i < message.length; i++) {
				output[i] = Integer.parseInt(textField[i].getText());
			}
		} catch (java.lang.NumberFormatException e) {
			return null;
		}

		return output;
	}

	public IModel getModel() {
		return this.m;
	}

	@Override
	public int getScale() {
		return bB.getScale();
	}
}
