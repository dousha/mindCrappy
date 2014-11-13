package mindCrappy;

import java.awt.event.MouseEvent;

public class BtnSetting extends Btn{
	private static final long serialVersionUID = 1L;
	public DialogSetting dlgSetting;
	private Framework fmwk;
	public BtnSetting(String name, int x, int y, int width, int height, Framework fmwk) {
		super(name, x, y, width, height);
		this.fmwk = fmwk;
	}
	
	public void isClicked(MouseEvent e){
		dlgSetting = new DialogSetting(this.fmwk);
	}

}
