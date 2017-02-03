package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Display extends JFrame implements IDisplay {

	private static final long serialVersionUID = 1L;

	Container cp;

	public Display() {
		createGUI();
	}

	private void createGUI() {

		new JFrame("Gizmo Ball");

		cp = this.getContentPane();
		addMenuBar();
		build();
		pack();
		setSize(new Dimension(800, 700));

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void build() {
		Build b = new Build();
		BuildBoard bb = new BuildBoard();
		cp.add(b, BorderLayout.LINE_START);
		cp.add(bb, BorderLayout.CENTER);
		this.setTitle("BUILD MODE");
	}

	public void run() {
		Run r = new Run();
		RunBoard rb = new RunBoard();
		cp.add(r, BorderLayout.LINE_START);
		cp.add(rb, BorderLayout.CENTER);
		this.setTitle("RUN MODE");
	}

	private void addMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");

		JMenuItem newBoard = new JMenuItem("New");
		//NewBoardListener
		JMenuItem load = new JMenuItem("Load");
		//LoadListener
		JMenuItem save = new JMenuItem("Save");
		//SaveListener
		JMenuItem exit = new JMenuItem("Exit");
		//ExitListener

		menu.add(newBoard);
		menu.add(load);
		menu.add(save);
		menu.addSeparator();
		menu.add(exit);

		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

}
