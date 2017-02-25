package view;

import java.io.File;

import model.IModel;

public interface IDisplay {
	public void build();

	public void run();

	public void changeBuildButtons(String b);

	public void changeMode(String m);

	public File saveDialog();

	public File loadDialog();

	public void errorPopup(String errorMessage);
	
	public void load();

	public IModel getModel();
	
	public int[] inputPopup(String[] message) ;
}