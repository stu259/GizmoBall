package controller;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import controller.buildListeners.AddAbsorberListener;
import controller.buildListeners.AddBallListener;
import controller.buildListeners.AddGizmosListener;
import controller.buildListeners.BindListener;
import controller.buildListeners.BuildListener;
import controller.buildListeners.ClearListener;
import controller.buildListeners.ConnectListener;
import controller.buildListeners.DeleteListener;
import controller.buildListeners.DisconnectListener;
import controller.buildListeners.FrictionListener;
import controller.buildListeners.GravityListener;
import controller.buildListeners.MoveListener;
import controller.buildListeners.RotateListener;
import controller.runListeners.RunListener;
import controller.runListeners.TickListener;
import model.IModel;
import model.Model;
import view.Display;
import view.IDisplay;

public class Driver {

	private static Map<String, ActionListener> listeners;
	private static BuildListener buildL;
	private static RunListener runL;
	private static TimerListener timer;

	public static void main(String[] args) {
		Model m = new Model();
		IDisplay d = new Display(m);
		createControllers(m, d);
		d.addListeners(listeners);
		d.addBuildListener(buildL);
		d.addRunListener(runL);
		d.addTimer(timer);
		d.start();
	}

	private static void createControllers(IModel m, IDisplay d) {
		buildL = new BuildListener();
		runL = new RunListener(m);
		timer = new TimerListener(m);

		listeners = new HashMap<>();
		listeners.put("aAL", new AddAbsorberListener(m, buildL, d));
		listeners.put("aBL", new AddBallListener(m, buildL, d));
		listeners.put("aGL", new AddGizmosListener(m, buildL, d));
		listeners.put("bindL", new BindListener(m, buildL, d));
		listeners.put("cL", new ClearListener(m));
		listeners.put("connectL", new ConnectListener(m, buildL, d));
		listeners.put("dL", new DeleteListener(m, buildL, d));
		listeners.put("disconnectL", new DisconnectListener(m, buildL, d));
		listeners.put("fL", new FrictionListener(m, d));
		listeners.put("gL", new GravityListener(m, d));
		listeners.put("ml", new MoveListener(m, buildL, d));
		listeners.put("rL", new RotateListener(m, buildL, d));

		listeners.put("tL", new TickListener(m));
		listeners.put("eL", new ExitActionListener());
		listeners.put("lL", new LoadListener(d, m));
		listeners.put("modeL", new ModeListener(d, m, timer));
		listeners.put("nBL", new NewBoardListener(d, m, timer));
		listeners.put("sL", new SaveListener(d, m));

	}
}
