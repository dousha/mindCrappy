package mindCrappy;

import java.awt.event.MouseEvent;

public class BtnSetting extends Btn{
	private static final long serialVersionUID = 1L;
	public DialogSetting dlgSetting;
	public BtnSetting(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
	}
	
	public void isClicked(MouseEvent e){
		dlgSetting = new DialogSetting();
	}

}
