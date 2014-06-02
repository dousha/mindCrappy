package mindCrappy;

import java.awt.event.MouseEvent;

public class BtnExit extends Btn{
	private static final long serialVersionUID = 1L;

	public BtnExit(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
	}
	
	public void isClicked(MouseEvent e){
		System.exit(0);
	}

}
