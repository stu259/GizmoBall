package view;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import controller.TimerListener;
import controller.buildListeners.BuildListener;
import controller.runListeners.RunListener;
import model.IModel;

public interface IDisplay {
	public void build();

	public void run();

	public void changeMode(String m);

	public File saveDialog();

	public File loadDialog();

	public void errorPopup(String errorMessage);

	public double[] inputPopup(String[] message, double[] min, double[] max, double[] init);

	public int getScale();

	public void changeText(String m);

	public void addListeners(Map<String, ActionListener> l);

	public void addBuildListener(BuildListener b);

	public void addRunListener(RunListener r);

	public void addTimer(TimerListener t);

	public void start();
}