package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class Display extends JFrame implements IDisplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private SaveListener sL;
	private LoadListener lL;
	private IModel m;

	public Display() {
		initialise();
		addMenuBar();
		build();
		tidy();
	}

	private void initialise() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
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
		setJMenuBar(menuBar);
	}
	
	public void build(){
		panel.add(new Build(this),BorderLayout.LINE_START);
		panel.add(new BuildBoard(),BorderLayout.CENTER);
		this.setTitle("BUILD MODE");
	}
	
	public void run(){
		panel.add(new Run(),BorderLayout.LINE_START);
		panel.add(new RunBoard(),BorderLayout.CENTER);
		this.setTitle("RUN MODE");
	}
	
	private void tidy() {
		panel.setSize(800,700);
		panel.setMaximumSize(new Dimension(800, 700));
		pack();
		setSize(new Dimension(800, 700));
		setMaximumSize(new Dimension(800, 700));
		setLocationRelativeTo(null);
		setVisible(true);
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

}
