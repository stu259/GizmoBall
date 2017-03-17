package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.TimerListener;
import controller.buildListeners.BuildListener;
import controller.runListeners.RunListener;
import model.IDrawableModel;
import model.Model;

public class Display implements IDisplay, IViewError {

	private JFrame frame;
	private JPanel buttons;
	private BuildButtons build;
	private RunButtons run;
	private JPanel boards;
	private BuildBoard bB;
	private RunBoard rB;
	private JTextField output;
	private Map<String, ActionListener> listeners;
	private IDrawableModel model;
	private BuildListener bL;
	private RunListener rL;
	private Container cp;
	private int boardSize;
	private TimerListener timer;

	public Display(Model m) {
		model =m;
	}

	public void addListeners(Map<String, ActionListener> l){
		listeners=l;
	}
	
	public void addBuildListener(BuildListener b){
		bL =b;
	}
	
	public void addRunListener(RunListener r){
		rL =r;
	}

	public void addTimer(TimerListener t){
		timer =t;
	}
	
	public void start(){
		initialise();
		addMenuBar();
		tidy();
	}
	
	private void initialise() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int size = (int)(screenSize.getHeight()-100)/100;
		size=size*100;
		boardSize=(int)size;
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
		newBoard.addActionListener(listeners.get("nBL"));
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(listeners.get("lL"));
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(listeners.get("sL"));
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(listeners.get("eL"));

		menu.add(newBoard);
		menu.add(load);
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);

		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
	}

	private void tidy() {
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
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

	public void changeText(String m) {
		output.setText(m);
	}

	public void build() {
		bB = new BuildBoard(boardSize, model);
		build = new BuildButtons(listeners, bL, bB);
		buttons.add(build, "build");
		boards.add(bB, "build");
	}

	public void run() {
		run = new RunButtons(listeners, rL, timer);
		rB = new RunBoard(boardSize, model);
		buttons.add(run, "run");
		boards.add(rB, "run");
	}

	public File saveDialog() {
		String filePath = (String) JOptionPane.showInputDialog(new JFrame(), "Enter name for save", "Save Dialog",
				JOptionPane.PLAIN_MESSAGE);
		if (filePath != null) {
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

	public double[] inputPopup(String[] message, double[] min, double[] max, double[] init) {
		JSlider[] slider = new JSlider[message.length];
		JPanel inputPopup = new JPanel(new BorderLayout());
		double[] output = new double[message.length];

		JPanel labels = new JPanel(new GridLayout(message.length, 1));
		for (int i = 0; i < message.length; i++) {
			labels.add(new JLabel(message[i]));
		}
		inputPopup.add(labels, BorderLayout.WEST);

		JPanel input = new JPanel(new GridLayout(message.length, 1));
		for (int i = 0; i < message.length; i++) {
			slider[i] = new JSlider(JSlider.HORIZONTAL, (int) (min[i] * 1000), (int) (max[i] * 1000),
					(int) (init[i] * 1000));
			Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
			for (double x = min[i]; x <= max[i]; x += max[i] / 5) {
				double temp;
				if (max[i] / 5 >= 1) {
					temp = Math.round(x);
					labelTable.put(new Integer((int) (temp * 1000)), new JLabel(Integer.toString((int)temp)));
				} else {
					temp = ((double) Math.round((x) * 1000)) / 1000;
					labelTable.put(new Integer((int) (temp * 1000)), new JLabel(Double.toString(temp)));
				}
				
			}
			slider[i].setLabelTable(labelTable);
			slider[i].setMinorTickSpacing((int) (max[i] * 1000) / 10);
			slider[i].setPaintTicks(true);
			slider[i].setPaintLabels(true);
			input.add(slider[i]);
		}
		inputPopup.add(input, BorderLayout.CENTER);

		JOptionPane.showMessageDialog(frame, inputPopup, "Input", JOptionPane.PLAIN_MESSAGE);

		for (int i = 0; i < message.length; i++) {
			output[i] = (double)slider[i].getValue() / 1000;
		}

		return output;
	}
	@Override
	public int getScale() {
		return bB.getScale();
	}

}
