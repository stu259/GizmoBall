package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.BuildListener;
import controller.LoadListener;
import controller.MagicKeyListener;
import controller.SaveListener;
import controller.runListeners.AbsorberListener;
import controller.runListeners.FlipperListener;
import model.IModel;
import model.Model;
import prototypes.FlipperBoard;

public class Display implements IDisplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SaveListener sL;
	private LoadListener lL;
	private Model m;
	private JFrame frame;
	private JPanel buttons;
	private BuildButtons build;
	private RunButtons run;
	private JPanel boards;
	private BuildBoard bB;
	private RunBoard rB;

	Container cp;

	public Display(Model model) {
		m = model;
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
		changeMode("build");
	}

	private void addMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		JMenuItem newBoard = new JMenuItem("New");
		// NewBoardListener
		JMenuItem load = new JMenuItem("Load");
		lL = new LoadListener(this, m);
		load.addActionListener(lL);
		JMenuItem save = new JMenuItem("Save");
		sL = new SaveListener(this, m);
		save.addActionListener(sL);

		JMenuItem exit = new JMenuItem("Exit");
		// ExitListener

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

	public void changeBuildButtons(String b) {
		build.changeButtons(b);
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

	public void build() {
		bB = new BuildBoard(500, 500);
		build = new BuildButtons(this, m, bB);
		buttons.add(build, "build");
		boards.add(bB, "build");
	}

	public void run() {
		run = new RunButtons(this);
		rB = new RunBoard(700, 700, m);
		buttons.add(run, "run");
		boards.add(rB, "run");
	}

	public File saveDialog() {
		String filePath = (String) JOptionPane.showInputDialog(new JFrame(), "Enter name for save", "Save Dialog",
				JOptionPane.PLAIN_MESSAGE);
		if (filePath.length()>0) {
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

	public void load() {
		buttons.add(new JPanel(), "blank");
		FlipperBoard bb = new FlipperBoard(700, 700, m);
		boards.add(bb, "load");
		CardLayout cardLayout = (CardLayout) buttons.getLayout();
		cardLayout.show(buttons, "blank");
		cardLayout = (CardLayout) boards.getLayout();
		cardLayout.show(boards, "load");
	}

	public int[] inputPopup(String[] message) {
		JTextField[] textField = new JTextField[message.length];
		JPanel inputPopup = new JPanel(new BorderLayout());
		int[] output = new int[message.length];

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
}
