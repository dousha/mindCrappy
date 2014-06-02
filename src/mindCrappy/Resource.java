package mindCrappy;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

// Useless now...
public class Resource {
	public Image btnN; // normal
	public Image btnP; // pressed
	public Image btnH; // holding
	public Image bkgnd; // background
	public Image icon; // icon
	public Image btnE; // exit
	public Image titl; // title bar
	public String path;
	public int randomseed;
	public int randomnum;
	
	public Resource(){
		path = "";
		try{
			icon = ImageIO.read(getClass().getResourceAsStream("./icon.png"));
			btnN = ImageIO.read(getClass().getResourceAsStream("./btn_normal.png"));
			btnH = ImageIO.read(getClass().getResourceAsStream("./btn_entered.png"));
			btnP = ImageIO.read(getClass().getResourceAsStream("./btn_clickin.png"));
			btnE = ImageIO.read(getClass().getResourceAsStream("./btn_exit.png"));
			titl = ImageIO.read(getClass().getResourceAsStream("./titlebar_default.png"));
		}
		catch(IOException ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
}

