package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.LoadListener;
import controller.SaveListener;
import model.IModel;
import model.Model;

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
	private Build build;
	private Run run;
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
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void createAndShowGUI() {

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
		build = new Build(this);
		bB = new BuildBoard(500, 500);
		buttons.add(build, "build");
		boards.add(bB, "build");
	}

	public void run() {
		run = new Run(this);
		rB = new RunBoard(500, 500);
		buttons.add(run, "run");
		boards.add(rB, "run");
	}

	public File saveDialog() {
		String filePath = (String) JOptionPane.showInputDialog(new JFrame(), "Enter name for save", "Customized Dialog",
				JOptionPane.PLAIN_MESSAGE);
		return new File(filePath + ".gizmo");

	}

	public File loadDialog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GIZMO FILES", "gizmo");
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
	
	public void load(){
		buttons.add(new JPanel(), "blank");
		FlipperBoard bb = new FlipperBoard(500, 500, m);
		boards.add(bb, "load");
		CardLayout cardLayout = (CardLayout) buttons.getLayout();
		cardLayout.show(buttons, "blank");
		cardLayout = (CardLayout) boards.getLayout();
		cardLayout.show(boards, "load");
		
	}
}
