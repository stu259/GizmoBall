package view;

import java.io.File;

import model.IModel;

public interface IDisplay {
	public void build();

	public void run();

	public void changeMode(String m);

	public File saveDialog();

	public File loadDialog();

	public void errorPopup(String errorMessage);
	
	public IModel getModel();
	
	public double[] inputPopup(String[] message) ;
	
	public int getScale();
	
	public void changeText(String m);
}