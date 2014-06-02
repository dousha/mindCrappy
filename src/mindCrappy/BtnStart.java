package mindCrappy;

import java.awt.event.MouseEvent;

public class BtnStart extends Btn{
	private static final long serialVersionUID = 1L;
	public LibraryReader lr = new LibraryReader();

	public BtnStart(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
	}
	
	public void isClicked(MouseEvent e){
		Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec(lr.read());
			System.out.println("Command : " + lr.read());
			System.out.println(p.toString()); // Just for clean the warnings
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
