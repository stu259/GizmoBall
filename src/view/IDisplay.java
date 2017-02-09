package view;

import java.io.File;

public interface IDisplay {
	public void build();
	
	public void run();
	
	public File saveDialog() ;
	
	public File loadDialog() ;

}