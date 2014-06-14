package mindCrappy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import mindCrappy.Framework.framework;

public class BtnExit extends Btn{
	private static final long serialVersionUID = 1L;
	public Assets assets;
	public framework f;

	public BtnExit(String name, int x, int y, int width, int height, framework fmwk) {
		super(name, x, y, width, height);
		f = fmwk;
		assets = f.assets;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(assets.btnE, 0, 0, 32, 32, null);
	}
	
	public void isClicked(MouseEvent e){
		System.exit(0);
	}

}
