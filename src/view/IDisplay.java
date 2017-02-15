package view;

import java.io.File;

public interface IDisplay {
	public void build();

	public void run();

	public void changeBuildButtons(String b);

	public void changeMode(String m);

	public File saveDialog();

	public File loadDialog();

	public void errorPopup(String errorMessage);
	
	public void load();

}